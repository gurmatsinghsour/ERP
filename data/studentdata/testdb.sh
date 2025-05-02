#!/bin/bash

CONTAINER="student_dbv2"
USER="admin"
DB="admindb"
DATA_DIR="./"  # Update if your CSVs are elsewhere

declare -A tables=(
  ["departments"]="Departments.csv"
  ["roles"]="Roles.csv"
  ["users"]="Users.csv"
  ["students"]="Students.csv"
  ["courses"]="Courses.csv"
  ["marks"]="Marks.csv"
  ["attendance"]="AttendanceFix.csv"
  ["model_history"]="Model_History.csv"
  ["predictions"]="Predictions.csv"
)

echo "🚀 Starting data import into database: $DB on container $CONTAINER"

for table in "${!tables[@]}"; do
  csv_file="$DATA_DIR/${tables[$table]}"

  if [[ ! -f "$csv_file" ]]; then
    echo "⚠️ File not found: $csv_file"
    continue
  fi

  echo "📦 Importing ${tables[$table]} into $table..."

  cat "$csv_file" | docker exec -i "$CONTAINER" psql -U "$USER" -d "$DB" -c "\COPY $table FROM STDIN WITH CSV HEADER"

  if [ $? -ne 0 ]; then
    echo "❌ Failed to import $table"
  else
    echo "✅ Imported $table"
  fi
done

echo "🎉 Data import completed!"
