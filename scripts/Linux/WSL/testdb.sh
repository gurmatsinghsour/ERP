#!/bin/bash

# PostgreSQL Docker container details
DB_NAME="admindb"
DB_USER="admin"
DB_HOST="10.0.0.100"
DB_PORT="5432"

# Export password so psql doesn't prompt
export PGPASSWORD="arcy"

# List of tables and corresponding CSV files
declare -A tables
tables=(
  ["departments"]="Departments.csv"
  ["roles"]="Roles.csv"
  ["users"]="Users.csv"
  ["students"]="Students.csv"
  ["courses"]="Courses.csv"
  ["marks"]="Marks.csv"
  ["attendance"]="Attendance.csv"
  ["model_history"]="Model_History.csv"
  ["predictions"]="Predictions.csv"
)

echo "🚀 Starting data import into database: $DB_NAME on $DB_HOST:$DB_PORT"

for table in "${!tables[@]}"; do
  file="${tables[$table]}"
  echo "📦 Importing $file into $table..."

  psql -h "$DB_HOST" -U "$DB_USER" -d "$DB_NAME" -p "$DB_PORT" -c "\copy $table FROM '$file' DELIMITER ',' CSV HEADER;"

  if [ $? -ne 0 ]; then
    echo "❌ Failed to import $file into $table"
  else
    echo "✅ Successfully imported $file into $table"
  fi
done

echo "✅ Data import completed."

# Unset the password for security
unset PGPASSWORD
