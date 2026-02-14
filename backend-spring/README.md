# EVMapper - Backend (Spring)

This README explains how to run the backend server locally.

## Prerequisites
- Java 17 (LTS) or newer installed and JAVA_HOME set.
- Maven 3.6+ (or Gradle if project uses it).

## If both of these given below are shown when you run it in terminal/powershell then you can run the project.
  - java -version (>jdk 17)
  - mvn -v (= 3.5.10)
  - psql -version

## Command to setup postgre sql

```
psql -U postgres
```
```
CREATE DATABASE ev_platform;
\c ev_platform
CREATE EXTENSION postgis;
```

Verify:
```
SELECT PostGIS_Version();
```


Set env vars (examples):
- PowerShell:
  - $Env:SPRING_DATASOURCE_URL = 'jdbc:postgresql://localhost:5432/evmapper'
- Bash:
  - export SPRING_DATASOURCE_URL='jdbc:postgresql://localhost:5432/evmapper'

If no external DB is configured the app may run with an embedded DB (H2) if provided by the project.

## Run locally (development)
From project root (backend-spring/):

Using Maven:
- mvn spring-boot:run
- mvn -Dspring-boot.run.profiles=dev spring-boot:run  (run specific profile)

Override port:
- mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=8081"
- java -jar target/*.jar --server.port=8081

## Build a jar and run
- mvn clean package
- java -jar target/<artifact>-<version>.jar

## Run tests
- mvn test

## Quick health check
If an endpoint exists (or actuator enabled):
- curl http://localhost:8080/actuator/health
Or try a known API endpoint:
- curl http://localhost:8080/api/health

## Useful commands summary
- Start (Maven): mvn spring-boot:run
- Build: mvn clean package
- Run jar: java -jar target/*.jar
- Tests: mvn test
