# EVMapper - Backend (Spring)

This README explains how to run the backend server locally.

## Prerequisites

- Java 17 (LTS) or newer installed and JAVA_HOME set
- Maven 3.6+ (verify with `mvn -v`)
- PostgreSQL 14+ with PostGIS extension

Verify prerequisites:
```bash
java -version    # Should show JDK 17+
mvn -v           # Should show Maven 3.6+
psql --version  # Should show PostgreSQL 14+
```

## Database Setup

```bash
psql -U postgres
```

Create the database:
```sql
CREATE DATABASE evmapper;
\c evmapper
CREATE EXTENSION postgis;
```

Verify PostGIS is installed:
```sql
SELECT PostGIS_Version();
```

## Configuration

Set environment variables before running:

**PowerShell:**
```powershell
$Env:SPRING_DATASOURCE_URL='jdbc:postgresql://localhost:5432/evmapper'
$Env:SPRING_DATASOURCE_USERNAME='postgres'
$Env:SPRING_DATASOURCE_PASSWORD='your_password'
```

**Bash:**
```bash
export SPRING_DATASOURCE_URL='jdbc:postgresql://localhost:5432/evmapper'
export SPRING_DATASOURCE_USERNAME='postgres'
export SPRING_DATASOURCE_PASSWORD='your_password'
```

If no external DB is configured, the app may run with an embedded H2 database (if available in the project).

## Run Locally (Development)

From project root (`backend-spring/`):

Using Maven:
```bash
mvn spring-boot:run
```

Run with specific profile:
```bash
mvn -Dspring-boot.run.profiles=dev spring-boot:run
```

Override port:
```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=8081"
```

## Build a JAR and Run

```bash
mvn clean package
java -jar target/<artifact>-<version>.jar
```

Run JAR on custom port:
```bash
java -jar target/*.jar --server.port=8081
```

## Run Tests

```bash
mvn test
```

## Health Check

If Spring Actuator is enabled:
```bash
curl http://localhost:8080/actuator/health
```

Or try a known API endpoint:
```bash
curl http://localhost:8080/api/health
```

## Useful Commands Summary

| Action | Command |
|--------|---------|
| Start (Maven) | `mvn spring-boot:run` |
| Build | `mvn clean package` |
| Run JAR | `java -jar target/*.jar` |
| Run tests | `mvn test` |
