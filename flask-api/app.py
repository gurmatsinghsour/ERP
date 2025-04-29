from flask import Flask, request, jsonify
import joblib
import numpy as np
from notebooks import fr 

app = Flask(__name__)

# Load models
midterm_model = joblib.load("midterm_model.pkl")
final_model = joblib.load("final_gpa_model.pkl")

@app.route('/predict/midterm', methods=['POST'])
def predict_midterm():
    data = request.get_json()
    features = np.array([[
        data['midterm_avg'],
        data['attendance_rate'],
        data['term'],
        data['gpa']
    ]])
    prediction = midterm_model.predict(features)[0]
    return jsonify({"prediction": "Pass" if prediction == 1 else "Fail"})

@app.route('/predict/final', methods=['POST'])
def predict_final():
    data = request.get_json()
    features = np.array([[
        data['overall_score'],
        data['attendance_rate'],
        data['term'],
        data['gpa']
    ]])
    gpa = final_model.predict(features)[0]
    return jsonify({"predicted_gpa": round(float(gpa), 2)})

# @app.route('/Register', methods=['POST'])
# def registerFace():
#     fr.register_face()


if __name__ == '__main__':
    app.run(debug=True, port=5000)
