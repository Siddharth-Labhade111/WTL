# Employee Task Management System

A Spring Boot application for the Engineering Department to manage employee details and track faculty task assignments in one place.

## Features

- Add and view employee records
- Assign tasks with category, priority, due date, and status
- Monitor pending, in-progress, completed, and overdue tasks
- View employee-wise task allocation
- Use the in-memory H2 database for quick testing

## Tech Stack

- Java 21
- Spring Boot 3
- Spring MVC
- Spring Data JPA
- Thymeleaf
- H2 Database

## Run the Project

```powershell
.\mvnw.cmd spring-boot:run
```

Open `http://localhost:8080`

H2 console: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:employeedb`
- Username: `sa`
- Password: leave blank
