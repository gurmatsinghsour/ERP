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

import os
import cv2
import numpy as np
import face_recognition
import datetime
import psycopg2

# Face encoding database
KNOWN_FACES_DIR = "/home/arcy/Projects/ERP/data/known"
TOLERANCE = 0.6
FRAME_THICKNESS = 3
FONT_THICKNESS = 2
MODEL = "cnn"  # or "hog"

# PostgreSQL DB credentials
DB_PARAMS = {
    'dbname': 'admindb',
    'user': 'admin',
    'password': 'arcy',
    'host': 'localhost',
    'port': 5432
}

print("Loading known faces...")
known_faces = []
known_names = []

for name in os.listdir(KNOWN_FACES_DIR):
    for filename in os.listdir(f"{KNOWN_FACES_DIR}/{name}"):
        image = face_recognition.load_image_file(f"{KNOWN_FACES_DIR}/{name}/{filename}")
        encodings = face_recognition.face_encodings(image)
        if encodings:
            encoding = encodings[0]
            known_faces.append(encoding)
            known_names.append(name)


def recognize_faces():
    print("Starting camera...")
    cap = cv2.VideoCapture(0)
    process_frame = True
    attendance_log = {}
    log_interval = 30  # seconds between logging same student's attendance

    while True:
        ret, frame = cap.read()
        if not ret:
            break

        if process_frame:
            small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)
            rgb_small_frame = small_frame[:, :, ::-1]

            face_locations = face_recognition.face_locations(rgb_small_frame, model=MODEL)
            face_encodings = face_recognition.face_encodings(rgb_small_frame, face_locations)

            for face_encoding, face_location in zip(face_encodings, face_locations):
                matches = face_recognition.compare_faces(known_faces, face_encoding, TOLERANCE)
                name = "Unknown"

                if True in matches:
                    match_index = matches.index(True)
                    name = known_names[match_index]
                    print(f"Recognized: {name}")

                    current_time = datetime.datetime.now()
                    last_seen = attendance_log.get(name)

                    if last_seen is None or (current_time - last_seen).total_seconds() > log_interval:
                        attendance_log[name] = current_time
                        status = "Late" if current_time.hour >= 9 else "Present"

                        try:
                            conn = psycopg2.connect(**DB_PARAMS)
                            cur = conn.cursor()

                            # Fetch student ID
                            cur.execute("SELECT student_id FROM students WHERE first_name = %s", (name,))
                            result = cur.fetchone()
                            if not result:
                                print(f"Student '{name}' not found in DB")
                            else:
                                student_id = result[0]
                                course_id = 1  # Placeholder; change as needed
                                class_date = current_time.date()

                                cur.execute("""
                                    INSERT INTO attendance (student_id, course_id, class_date, status)
                                    VALUES (%s, %s, %s, %s)
                                """, (student_id, course_id, class_date, status))
                                conn.commit()
                                print(f"Attendance recorded for {name} - {status}")

                            cur.close()
                            conn.close()
                        except Exception as e:
                            print(f"DB Error: {e}")

                top_left = (face_location[3]*4, face_location[0]*4)
                bottom_right = (face_location[1]*4, face_location[2]*4)

                color = (0, 255, 0) if name != "Unknown" else (0, 0, 255)
                cv2.rectangle(frame, top_left, bottom_right, color, FRAME_THICKNESS)
                cv2.putText(frame, name, (top_left[0], top_left[1] - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.8, color, FONT_THICKNESS)

        process_frame = not process_frame

        cv2.imshow("Attendance Face Recognition", frame)
        if cv2.waitKey(1) & 0xFF == ord("q"):
            break

    cap.release()
    cv2.destroyAllWindows()


def register_face():
    name = input("Enter the student's first name (must match existing database): ")

    try:
        conn = psycopg2.connect(**DB_PARAMS)
        cur = conn.cursor()

        cur.execute("SELECT student_id FROM students WHERE first_name = %s", (name,))
        result = cur.fetchone()

        if not result:
            print(f"Student '{name}' not found in database. Please add student from the Java system first.")
            cur.close()
            conn.close()
            return

        cur.close()
        conn.close()
    except Exception as e:
        print(f"❌ DB Error: {e}")
        return

    save_path = os.path.join(KNOWN_FACES_DIR, name)
    os.makedirs(save_path, exist_ok=True)

    cap = cv2.VideoCapture(0)
    print("[INFO] Press SPACE to capture, ESC to exit.")

    count = 0
    while True:
        ret, frame = cap.read()
        if not ret:
            break

        cv2.imshow("Register Face", frame)
        key = cv2.waitKey(1)

        if key % 256 == 27:
            print("[INFO] Exiting registration.")
            break
        elif key % 256 == 32:
            img_name = f"{name}_{count}.jpg"
            img_path = os.path.join(save_path, img_name)
            cv2.imwrite(img_path, frame)
            print(f"[INFO] Saved {img_path}")
            count += 1

    cap.release()
    cv2.destroyAllWindows()



if __name__ == '__main__':
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
