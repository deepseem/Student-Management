# Student Management API

Checklist
- [x] Add README describing the project
- [x] Explain how to build and run locally
- [x] Document available HTTP endpoints with examples
- [x] Note security, configuration, and Postman collection location

Overview
This is the Student Management microservice (Spring Boot). It provides CRUD APIs to manage students and includes OpenAPI/Swagger documentation.

Project structure (important files)
- `src/main/java/com/example/studentmanagement/controller/StudentController.java` — REST endpoints
- `src/main/java/com/example/studentmanagement/entity/Student.java` — Student model
- `src/main/resources/application.properties` — default configuration (port, datasource, jwt.secret, etc.)
- `postman/collections/` — Postman collections for manual testing

Prerequisites
- Java 17+ (project uses modern Spring Boot and Jakarta packages)
- Maven 3.6+
- MySQL (or adjust `spring.datasource.*` to your DB) — default: `jdbc:mysql://localhost:3306/student_management`
- (Optional) Redis if you plan to enable caching: host `localhost:6379`

Build
From the project root run:

```
mvn clean package -DskipTests
```

Run

1) Run with Maven:

```
mvn spring-boot:run
```

2) Or run the packaged jar:

```
java -jar target/student-service-0.0.1-SNAPSHOT.jar
```

The application default port is configured in `application.properties` as `server.port=8082`.

Configuration
- Database: edit `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password` in `src/main/resources/application.properties`.
- JWT secret: `jwt.secret` is present in `application.properties`. Note: current code has JWT filter/util commented out (see Security section).

Security
- Currently the `SecurityConfig` allows all requests (security is permissive). There is a `JwtFilter` and `JwtUtil` implemented but commented-out in the source. To enable JWT authentication:
  1. Uncomment and wire the `JwtFilter` in `SecurityConfig`. Ensure `JwtFilter` and `JwtUtil` packages are active (remove block comments) and the `jwt.secret` property is set to a sufficiently strong secret.
  2. Adjust `SecurityConfig` to require authentication for endpoints and permit access to Swagger/UI and API docs.

API Endpoints
Base URL: http://localhost:8082/api/students

1) Create student
- POST /api/students
- Body (JSON):

```
{
  "name": "Deepak",
  "email": "deepak@example.com",
  "course": "Computer Science",
  "age": 20
}
```

2) Get all students
- GET /api/students

3) Get student by id
- GET /api/students/{id}

4) Update student
- PUT /api/students/{id}
- Body: same shape as create (partial updates should send full object as service expects)

5) Delete student
- DELETE /api/students/{id}

Swagger / OpenAPI
- Swagger UI (springdoc): http://localhost:8082/swagger-ui/index.html
  or http://localhost:8082/swagger-ui.html

Sample curl requests

Create:

```
curl -X POST http://localhost:8082/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Deepak","email":"deepak@example.com","course":"CS","age":20}'
```

Get all:

```
curl http://localhost:8082/api/students
```

Get one:

```
curl http://localhost:8082/api/students/1
```

If you enable JWT auth (see Security), add the Authorization header:

```
-H "Authorization: Bearer <JWT_TOKEN>"
```

Postman
- A Postman collection is available under the `postman/collections/` folder. Import it into Postman to quickly exercise the APIs.

Notes & Security Considerations
- Do NOT commit production credentials to source control. The `application.properties` in this repo contains example DB credentials — replace with environment variables or a secure secrets manager for production.
- Ensure `jwt.secret` is long and random if you enable JWT. Prefer storing it in an env var or vault.

Troubleshooting
- If the application fails to start due to DB errors, verify MySQL is running and the database `student_management` exists or update the URL to point at an available DB. The project uses `spring.jpa.hibernate.ddl-auto=update` which will attempt to create/update tables on startup.
- If port 8082 is in use change `server.port` in `application.properties`.

Further improvements
- Add DTOs and validation for request payloads
- Add integration and unit tests
- Add docker-compose for MySQL + Redis for local development
- Re-enable and test JWT security end-to-end

License
This repository does not include a license file. Add a LICENSE if you intend to publish.

