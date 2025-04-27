#!/bin/bash

# 1. Create a Docker network (optional, only if needed for multi-container setups)
docker network create net 2>/dev/null || echo "Network 'net' already exists."

# 2. Set environment variables
USER_NAME="admin"
DBNAME="admindb"
CONTAINER_NAME="student_dbv2"
DB_PASSWORD="arcy"

# 3. Run the PostgreSQL container
docker run -d \
  --name "$CONTAINER_NAME" \
  --network net \
  -e POSTGRES_USER="$USER_NAME" \
  -e POSTGRES_PASSWORD="$DB_PASSWORD" \
  -e POSTGRES_DB="$DBNAME" \
  -p 5432:5432 \
  postgres

# 4. Update config.properties with localhost IP
CONFIG_FILE="/home/arcy/ERP/core/backend/src/main/resources/config.properties"

# Use sed to update db.url to use localhost
sed -i "s|^db.url=.*|db.url=jdbc:postgresql://localhost:5432/$DBNAME|" "$CONFIG_FILE"

echo "✅ PostgreSQL container '$CONTAINER_NAME' started."
echo "✅ config.properties updated with: jdbc:postgresql://localhost:5432/$DBNAME"
