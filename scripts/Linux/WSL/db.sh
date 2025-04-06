#!/bin/bash

USER="admin"
DB_NAME="admindb"
PASSWORD="arcy"

# Define an array of SQL commands
sql_statements=(
"
CREATE TABLE departments (
    department_id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    code VARCHAR(10) UNIQUE NOT NULL
);
"
"
CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(20) UNIQUE NOT NULL
);
"
"
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    role_id INT REFERENCES roles(role_id),
    full_name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    created_at TIMESTAMP DEFAULT NOW()
);
"
"
CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    student_number VARCHAR(20) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    age SMALLINT,
    department_id INT REFERENCES departments(department_id),
    term SMALLINT NOT NULL,
    gpa NUMERIC(3,2),
    user_id INT UNIQUE REFERENCES users(user_id)
);
"
"
CREATE TABLE courses (
    course_id SERIAL PRIMARY KEY,
    course_code VARCHAR(20) UNIQUE NOT NULL,
    course_name VARCHAR(100) NOT NULL,
    department_id INT REFERENCES departments(department_id),
    credit_hours INT,
    term_offered SMALLINT
);
"
"
CREATE TABLE marks (
    mark_id SERIAL PRIMARY KEY,
    student_id INT REFERENCES students(student_id),
    course_id INT REFERENCES courses(course_id),
    assessment VARCHAR(50) NOT NULL,
    score NUMERIC(5,2) NOT NULL,
    max_score NUMERIC(5,2) NOT NULL,
    scored_at DATE NOT NULL
);
"
"
CREATE TABLE attendance (
    attendance_id SERIAL PRIMARY KEY,
    student_id INT REFERENCES students(student_id),
    course_id INT REFERENCES courses(course_id),
    class_date DATE NOT NULL,
    status VARCHAR(10) NOT NULL
);
"
"
CREATE TABLE model_history (
    model_id SERIAL PRIMARY KEY,
    version_name VARCHAR(50),
    description TEXT,
    updated_by INT REFERENCES users(user_id),
    updated_at TIMESTAMP DEFAULT NOW()
);
"
"
CREATE TABLE predictions (
    prediction_id SERIAL PRIMARY KEY,
    student_id INT REFERENCES students(student_id),
    term SMALLINT NOT NULL,
    predicted_outcome VARCHAR(10) NOT NULL,
    probability NUMERIC(4,3) NOT NULL,
    model_version_id INT REFERENCES model_history(model_id),
    predicted_at TIMESTAMP DEFAULT NOW()
);
"
)

# Execute each SQL command individually
for sql in "${sql_statements[@]}"; do
  echo "Executing SQL:"
  echo "$sql"
  docker exec -i student_dbv2 psql -U "$USER" -d "$DB_NAME" -c "$sql"
  
  if [ $? -eq 0 ]; then
    echo "Executed successfully."
  else
    echo "Error executing statement."
  fi
done
