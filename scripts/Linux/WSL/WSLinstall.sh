docker network create net


USER_NAME="admin"
DBNAME="admindb"


CONTAINER_NAME="student_dbv2"
docker run -d \
  --name "$CONTAINER_NAME" \
  -e POSTGRES_USER="$USER" \
  -e POSTGRES_PASSWORD=arcy \
  -e POSTGRES_DB="$DBNAME" \
  -p 5432:5432 \
  -d postgres

WIN_IP=$(ipconfig.exe | grep -i "IPv4 Address" | head -n 1 | awk -F: '{print $2}' | sed 's/\r//g' | xargs)

# 2. Check if we got an IP address
if [ -z "$WIN_IP" ]; then
  echo "Could not detect Windows IP from ipconfig!"
  exit 1
fi

echo "Detected Windows IP: $WIN_IP"

# 3. Update config.properties (in-place) to use this IP for db.url
#    Adjust path to config.properties if it's elsewhere.
sed -i "s|^db.url=.*|db.url=jdbc:postgresql://${WIN_IP}:5432/mydb|" /home/arcys/ERP/core/backend/src/main/resources/config.properties

echo "config.properties updated with: jdbc:postgresql://${WIN_IP}:5432/admindb"


