#!/bin/bash


docker network create net 2>/dev/null

USER_NAME="admin"
DBNAME="admindb"
CONTAINER_NAME="student_dbv2"

docker run -d \
  --name "$CONTAINER_NAME" \
  --network net \
  -e POSTGRES_USER="$USER_NAME" \
  -e POSTGRES_PASSWORD="arcy" \
  -e POSTGRES_DB="$DBNAME" \
  -p 5432:5432 \
  postgres:latest


CONFIG_PATH="/home/arcys/ERP/core/backend/src/main/resources/config.properties"

if [ -f "$CONFIG_PATH" ]; then
  sed -i "s|^db.url=.*|db.url=jdbc:postgresql://localhost:5432/admindb|" "$CONFIG_PATH"
  echo "✅ config.properties updated with: jdbc:postgresql://localhost:5432/admindb"
else
  echo "❌ config.properties not found at: $CONFIG_PATH"
fi
