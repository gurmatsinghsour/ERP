#!/bin/bash

USER="admin"
DB_NAME="admindb"

# Ordered for safe FK removal
drop_statements=(
  "DROP TABLE IF EXISTS predictions CASCADE;"
  "DROP TABLE IF EXISTS model_history CASCADE;"
  "DROP TABLE IF EXISTS attendance CASCADE;"
  "DROP TABLE IF EXISTS marks CASCADE;"
  "DROP TABLE IF EXISTS courses CASCADE;"
  "DROP TABLE IF EXISTS students CASCADE;"
  "DROP TABLE IF EXISTS users CASCADE;"
  "DROP TABLE IF EXISTS roles CASCADE;"
  "DROP TABLE IF EXISTS departments CASCADE;"
)

echo "⚠️ Dropping all ERP-related tables from $DB_NAME..."

for sql in "${drop_statements[@]}"; do
  echo "🧹 Running: $sql"
  docker exec -i student_dbv2 psql -U "$USER" -d "$DB_NAME" -c "$sql"
done

echo "✅ All tables dropped successfully."
