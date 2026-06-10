# NaveenSystems Services - Spring Boot Application

## Prerequisites

### Required Software
- **Java 26+** (JDK-26.0.1 or higher)
- **PostgreSQL 12+** (Database server)
- **Gradle 9.5+** or use the included `gradlew.bat`

### Database Setup
1. Start PostgreSQL server on localhost:5432
2. Create database:
   ```sql
   CREATE DATABASE naveensys_db;
   ```
3. Default credentials (from application.yaml):
   - Username: `postgres`
   - Password: `postgres`

## Running the Application

### Method 1: Using Gradle (Command Line)
```bash
cd naveensysservices
.\gradlew.bat bootRun
```

### Method 2: Using IntelliJ IDEA
1. Open project in IntelliJ IDEA
2. Press **Shift + F10** to run
3. Or press **Shift + F9** for debug mode

### Method 3: Build WAR and Run
```bash
.\gradlew.bat build
java -jar build/libs/naveensysservices-0.0.1-SNAPSHOT.war
```

## Application Configuration

### Server
- **Port**: 8080
- **Context Path**: /
- **Base URL**: http://localhost:8080

### Database
- **Type**: PostgreSQL
- **URL**: jdbc:postgresql://localhost:5432/naveensys_db
- **Auto DDL**: Update (hibernate.ddl-auto=update)
- **HikariCP Pool**: 2-5 connections

## API Endpoints

### User Management
All endpoints are prefixed with `/api/users`

#### Create User
```
POST /api/users
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "1234567890",
  "address": "123 Main St",
  "city": "New York",
  "state": "NY",
  "zipCode": "10001"
}
```

#### Get All Users
```
GET /api/users
```

#### Get User by ID
```
GET /api/users/{id}
```

#### Get User by Email
```
GET /api/users/email/{email}
```

#### Update User
```
PUT /api/users/{id}
Content-Type: application/json

{
  "name": "Jane Doe",
  "phone": "9876543210",
  ...
}
```

#### Delete User
```
DELETE /api/users/{id}
```

#### Delete All Users
```
DELETE /api/users
```

## Logging

- **Root Log Level**: INFO
- **Application Log Level**: DEBUG (com.naveensys.services)
- **Log Pattern**: `%d{yyyy-MM-dd HH:mm:ss} - %msg%n`

## Health Check

Access health endpoint:
```
GET http://localhost:8080/actuator/health
```

## Troubleshooting

### Docker Not Found Error
✅ **FIXED** - Docker Compose auto-start is disabled in application.yaml

### Database Connection Error
- Verify PostgreSQL is running on localhost:5432
- Check database credentials in application.yaml
- Ensure `naveensys_db` database exists

### Port 8080 Already in Use
- Change port in `application.yaml` under `server.port`
- Or kill the process using port 8080

## Project Structure
```
src/main/java/com/naveensys/services/
├── ServicesApplication.java       (Main Spring Boot Application)
├── ServletInitializer.java        (WAR deployment support)
├── config/
│   └── GlobalExceptionHandler.java (Global error handling)
├── controller/
│   └── UserController.java        (REST API endpoints)
├── service/
│   └── UserService.java           (Business logic)
├── entity/
│   └── User.java                  (JPA entity)
├── dto/
│   └── UserDTO.java               (Data transfer object)
└── repository/
    └── UserRepository.java        (Data access layer)
```

## Features
✅ RESTful API for user management
✅ Spring Data JPA integration
✅ PostgreSQL database support
✅ Transaction management
✅ Global exception handling
✅ CORS enabled for API endpoints
✅ HikariCP connection pooling
✅ Actuator health checks
✅ WAR deployment support

## Testing

Run tests with:
```bash
.\gradlew.bat test
```

## Dependencies
- Spring Boot 4.0.6
- Spring Data JPA
- Spring Data REST
- PostgreSQL Driver
- Lombok
- Junit 5

