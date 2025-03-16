docker network create net

docker run -d \
  --name "$CONTAINER_NAME" \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=arcy \
  -e POSTGRES_DB=admindb \
  -p 5432:5432 \
  -d postgres


@echo off
REM ----------------------------------------
REM 1) Parse the FIRST "IPv4 Address" line
REM    from ipconfig output
REM ----------------------------------------
for /f "tokens=2 delims=:" %%A in ('ipconfig ^| findstr /i "IPv4 Address"') do (
    set "line=%%A"
    REM Remove leading space
    set "line=%line:~1%"
    set IP=%line%
    goto :FOUND
)

:FOUND
IF "%IP%"=="" (
    echo ERROR: Could not detect Windows IP from ipconfig!
    exit /b 1
)

echo Detected IP: %IP%

REM ----------------------------------------
REM 2) Update config.properties
REM    - Replaces a line starting with 'db.url='
REM      with 'db.url=jdbc:postgresql://<IP>:5432/mydb'
REM ----------------------------------------
powershell -Command ^
  "(Get-Content /home/arcys/ERP/core/backend/src/main/resources/config.properties) ^
    -replace '^db.url=.*','db.url=jdbc:postgresql://%IP%:5432/admindb' ^
    | Out-File config.properties"

echo config.properties updated to use IP: %IP%
pause
