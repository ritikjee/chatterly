-- Check if the database 'auth_service' exists; create it if it doesn't
SELECT 'CREATE DATABASE auth_service'
WHERE NOT EXISTS (
    SELECT FROM pg_database WHERE datname = 'auth_service'
)\gexec
