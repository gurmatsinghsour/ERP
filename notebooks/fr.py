# -*- coding: utf-8 -*-
"""
Simple Face Recognition Attendance System

This script uses the face_recognition library (based on dlib) and OpenCV
to perform basic face registration and recognition for attendance purposes.

Requirements:
- Python 3.6+
- opencv-python
- face_recognition (which requires dlib and potentially cmake)
- numpy

Installation:
pip install opencv-python numpy face_recognition

Note: Installing dlib/face_recognition can sometimes be tricky.
Ensure you have cmake installed (pip install cmake) and potentially
a C++ compiler (like Visual Studio Build Tools on Windows).
Refer to face_recognition documentation for detailed installation help.
"""

import face_recognition
import cv2
import numpy as np
import os
import pickle # Used for saving/loading known faces data
import time
import datetime

# --- Configuration ---
KNOWN_FACES_DIR = "known_faces_data" # Directory to store face images (optional, for reference)
ENCODINGS_FILE = "known_faces.dat" # File to store encodings and names
TOLERANCE = 0.5 # Lower tolerance means stricter matching (0.6 is default)
FRAME_THICKNESS = 3
FONT_THICKNESS = 2
MODEL = "hog" # 'hog' is faster on CPU, 'cnn' is more accurate but requires GPU/CUDA

# --- Helper Functions ---

def load_known_faces(encodings_file=ENCODINGS_FILE):
    """Loads known face encodings and names from a file."""
    known_face_encodings = []
    known_face_names = []
    if os.path.exists(encodings_file):
        try:
            with open(encodings_file, 'rb') as f:
                data = pickle.load(f)
                known_face_encodings = data['encodings']
                known_face_names = data['names']
            print(f"Loaded {len(known_face_names)} known faces.")
        except Exception as e:
            print(f"Error loading known faces file: {e}. Starting fresh.")
            # If file is corrupted or incompatible, start fresh
            if os.path.exists(encodings_file):
                 os.remove(encodings_file) # Remove corrupted file
    return known_face_encodings, known_face_names

def save_known_faces(known_face_encodings, known_face_names, encodings_file=ENCODINGS_FILE):
    """Saves known face encodings and names to a file."""
    try:
        with open(encodings_file, 'wb') as f:
            pickle.dump({'encodings': known_face_encodings, 'names': known_face_names}, f)
        print(f"Saved {len(known_face_names)} known faces.")
    except Exception as e:
        print(f"Error saving known faces file: {e}")

def register_face():
    """Registers a new face via webcam."""
    known_face_encodings, known_face_names = load_known_faces()

    name = input("Enter the name of the person: ")
    if not name:
        print("Name cannot be empty.")
        return

    if name in known_face_names:
        print(f"{name} is already registered. Do you want to overwrite? (y/n)")
        overwrite = input().lower()
        if overwrite != 'y':
            print("Registration cancelled.")
            return
        else:
            # Find existing entry and remove it before adding new one
            indices_to_remove = [i for i, n in enumerate(known_face_names) if n == name]
            for i in sorted(indices_to_remove, reverse=True):
                del known_face_encodings[i]
                del known_face_names[i]
            print(f"Removed existing entries for {name}.")


    print("\nStarting webcam for registration...")
    print("Position your face clearly in the center of the frame.")
    print("Press 'c' to capture the image when ready. Press 'q' to quit.")

    video_capture = cv2.VideoCapture(0) # 0 is usually the default webcam

    if not video_capture.isOpened():
        print("Error: Could not open webcam.")
        return

    captured_encoding = None
    while True:
        ret, frame = video_capture.read()
        if not ret:
            print("Error: Failed to capture frame.")
            break

        # Display the frame
        cv2.imshow('Registration - Press C to capture, Q to quit', frame)

        key = cv2.waitKey(1) & 0xFF

        if key == ord('q'):
            print("Registration cancelled by user.")
            break
        elif key == ord('c'):
            print("Attempting to capture face...")
            # Convert frame to RGB (face_recognition uses RGB)
            rgb_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

            # Find face locations
            face_locations = face_recognition.face_locations(rgb_frame, model=MODEL)

            if len(face_locations) == 1:
                print("Face detected. Encoding face...")
                # Encode the detected face
                # Use known_face_locations to ensure encoding uses the detected area
                face_encodings = face_recognition.face_encodings(rgb_frame, known_face_locations=face_locations)
                if face_encodings:
                    captured_encoding = face_encodings[0] # Get the first (and only) encoding
                    print("Face encoded successfully!")

                    # Optional: Save the captured image for reference
                    if not os.path.exists(KNOWN_FACES_DIR):
                        os.makedirs(KNOWN_FACES_DIR)
                    img_path = os.path.join(KNOWN_FACES_DIR, f"{name.replace(' ', '_')}_{int(time.time())}.png")
                    cv2.imwrite(img_path, frame)
                    print(f"Reference image saved to {img_path}")

                    break # Exit loop after successful capture
                else:
                     print("Could not encode the detected face. Try again.")
            elif len(face_locations) == 0:
                print("No face detected in the frame. Please position yourself clearly.")
            else:
                print("Multiple faces detected. Please ensure only one person is in the frame.")

    video_capture.release()
    cv2.destroyAllWindows()

    if captured_encoding is not None:
        known_face_encodings.append(captured_encoding)
        known_face_names.append(name)
        save_known_faces(known_face_encodings, known_face_names)
        print(f"Registration successful for {name}.")
    else:
        print("Registration failed.")


def recognize_faces():
    """Recognizes faces using the webcam and marks attendance."""
    known_face_encodings, known_face_names = load_known_faces()

    if not known_face_encodings:
        print("No known faces registered. Please register faces first.")
        return

    print("\nStarting webcam for recognition...")
    print("Press 'q' to quit.")

    video_capture = cv2.VideoCapture(0)
    if not video_capture.isOpened():
        print("Error: Could not open webcam.")
        return

    # Attendance tracking
    attendance_log = {} # Dictionary to store {name: timestamp}
    log_interval = 30 # Log attendance for a person only once every X seconds

    while True:
        ret, frame = video_capture.read()
        if not ret:
            print("Error: Failed to capture frame.")
            break

        # Resize frame for faster processing (optional)
        # small_frame = cv2.resize(frame, (0, 0), fx=0.5, fy=0.5)
        # rgb_small_frame = cv2.cvtColor(small_frame, cv2.COLOR_BGR2RGB)
        rgb_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB) # Use full frame if not resizing

        # Find all face locations and encodings in the current frame
        face_locations = face_recognition.face_locations(rgb_frame, model=MODEL)
        face_encodings = face_recognition.face_encodings(rgb_frame, face_locations)

        face_names = []
        for face_encoding in face_encodings:
            # See if the face is a match for the known face(s)
            matches = face_recognition.compare_faces(known_face_encodings, face_encoding, tolerance=TOLERANCE)
            name = "Unknown" # Default to Unknown

            # Use the known face with the smallest distance to the new face
            face_distances = face_recognition.face_distance(known_face_encodings, face_encoding)
            best_match_index = np.argmin(face_distances)

            if matches[best_match_index]:
                name = known_face_names[best_match_index]

                # --- Attendance Logic ---
                current_time = time.time()
                last_seen_time = attendance_log.get(name)

                if last_seen_time is None or (current_time - last_seen_time) > log_interval:
                    timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                    attendance_log[name] = current_time
                    print(f"Attendance Marked: {name} at {timestamp}")
                    # Here you could write to a CSV file or database instead of printing
                    # with open("attendance.csv", "a") as f:
                    #     f.write(f"{name},{timestamp}\n")

            face_names.append(name)

        # Display the results
        for (top, right, bottom, left), name in zip(face_locations, face_names):
            # Scale back up face locations if using smaller frame for detection
            # top *= 2
            # right *= 2
            # bottom *= 2
            # left *= 2

            # Draw a box around the face
            color = (0, 255, 0) if name != "Unknown" else (0, 0, 255) # Green for known, Red for unknown
            cv2.rectangle(frame, (left, top), (right, bottom), color, FRAME_THICKNESS)

            # Draw a label with a name below the face
            cv2.rectangle(frame, (left, bottom - 35), (right, bottom), color, cv2.FILLED)
            font = cv2.FONT_HERSHEY_DUPLEX
            cv2.putText(frame, name, (left + 6, bottom - 6), font, 0.8, (255, 255, 255), FONT_THICKNESS)

        # Display the resulting image
        cv2.imshow('Face Recognition Attendance - Press Q to quit', frame)

        # Hit 'q' on the keyboard to quit!
        if cv2.waitKey(1) & 0xFF == ord('q'):
            print("Stopping recognition.")
            break

    # Release handle to the webcam
    video_capture.release()
    cv2.destroyAllWindows()
    print("Attendance Log for this session:", attendance_log)


# --- Main Execution ---
if __name__ == "__main__":
    # Optional: Initialize attendance file header if it doesn't exist
    # if not os.path.exists("attendance.csv"):
    #     with open("attendance.csv", "w") as f:
    #         f.write("Name,Timestamp\n")

    while True:
        print("\n--- Face Recognition Attendance System ---")
        print("1. Register New Face")
        print("2. Start Recognition / Attendance")
        print("3. Exit")
        choice = input("Enter your choice (1/2/3): ")

        if choice == '1':
            register_face()
        elif choice == '2':
            recognize_faces()
        elif choice == '3':
            print("Exiting program.")
            break
        else:
            print("Invalid choice. Please enter 1, 2, or 3.")

