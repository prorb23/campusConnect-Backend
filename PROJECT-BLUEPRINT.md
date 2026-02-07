# PROJECT-BLUEPRINT.md
# CampusConnect Backend (Spring Boot)

CampusConnect is a LinkedIn + Job Portal–style backend platform for students and recruiters, built using Spring Boot with production-grade architecture and best practices.

This document represents the **current authoritative project state** and can be used to resume work in a new chat or by a new developer **without loss of context**.

---

## 🧰 Tech Stack

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA (Hibernate)
- MySQL
- Jakarta Validation
- Lombok
- Springdoc OpenAPI (Swagger)
- Spring Security (upcoming)
- JWT (upcoming)

---

## 🧱 Architecture & Design Principles

- Layered architecture:
  - Controller → Service → Repository
- DTO-based APIs (entities never exposed)
- Constructor-based dependency injection
- Centralized exception handling
- RESTful, versioned APIs (`/api/v1`)
- Database-level constraints + service-level checks
- Validation enforced at API boundary
- Thin controllers, business logic in services

---

## 📂 Package Structure

```
com.rbslayer.campusconnectbackend
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── repository
├── service
│   └── impl
├── exception
├── config
└── CampusconnectBackendApplication.java
```

---

## 🚀 Current Milestone

### ✅ Milestone 1: Student CRUD API — COMPLETED & STABLE

All functionality in this milestone is implemented, tested, and working correctly.

---

## ✅ Implemented Features (Milestone 1)

### 🔧 Configuration
- `application.properties` configured
- MySQL database connected successfully
- Hibernate dialect explicitly set for MySQL
- `ddl-auto=update` used for development
- Swagger UI enabled

---

### 👨‍🎓 Student Module

#### Entity
- `Student` entity with:
  - Auto-generated primary key
  - Unique constraints on `email` and `phone`
  - Audit fields: `createdAt`, `updatedAt`
  - JPA lifecycle callbacks (`@PrePersist`, `@PreUpdate`)

#### Repository
- `StudentRepository` extends `JpaRepository`
- Custom methods:
  - `existsByEmail`
  - `existsByPhone`

#### DTOs
- `StudentCreateRequest`
- `StudentUpdateRequest`
- `StudentResponse`

#### Validation
- Jakarta Validation annotations
- Correct type-specific constraints:
  - `@NotBlank` for `String`
  - `@NotNull`, `@Min`, `@Max` for `Integer`
- Regex validation for Indian phone numbers:
  - Regex: `^[6-9]\\d{9}$`
  - Correct Java escaping applied

#### Service Layer
- `StudentService` interface
- `StudentServiceImpl` implementation
- Business logic includes:
  - Duplicate email/phone checks
  - Entity ↔ DTO mapping
  - Resource existence checks
- Mapping handled via private helper methods

#### Controller Layer
- `StudentController` exposing REST endpoints
- Endpoints:
  ```
  POST   /api/v1/students
  GET    /api/v1/students
  GET    /api/v1/students/{id}
  PUT    /api/v1/students/{id}
  DELETE /api/v1/students/{id}
  ```
- Proper HTTP status codes
- `@Valid` used for request validation

---

### ⚠️ Exception Handling

- Global exception handling using `@RestControllerAdvice`
- Custom exceptions:
  - `ResourceNotFoundException`
  - `DuplicateResourceException`
- Framework exceptions handled:
  - `MethodArgumentNotValidException`
  - `DataIntegrityViolationException`
- Standardized API error response structure:
  - `timestamp`
  - `status`
  - `error`
  - `message`
  - `path`
  - `validationErrors` (optional)

---

### 🧪 Testing

- APIs tested via Swagger UI
- APIs tested via Postman
- Validation edge cases verified
- Database constraint violations verified
- Regex and validation bugs fixed

---

## 📘 Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🧠 Key Decisions Locked In

- Validation annotations are type-specific
- Java regex strings require correct escaping
- DB constraints are the final authority
- Service-level checks complement DB constraints
- Constructor-based DI with Lombok
- Method references used for mapping
- Optional fields omitted from JSON when null

---

## 🔜 Upcoming Milestones

### 🔐 Milestone 2: Authentication & Authorization
- Student registration & login
- Spring Security configuration
- JWT generation & validation
- Role-based access control
- Securing existing endpoints

### 💼 Milestone 3: Job Management
- Recruiter job posting
- Student job browsing
- Search & filters

### 📄 Milestone 4: Job Applications
- Apply to jobs
- Track applications
- Application status updates

### 📰 Milestone 5: Social / Networking
- Posts (LinkedIn-style)
- Likes & comments
- Connections

### 🔔 Milestone 6: Notifications
- Job & application notifications
- Connection notifications

---

## 🎯 Project Status

**Milestone 1: ✅ COMPLETED & VERIFIED**

Project is ready to proceed directly with **Milestone 2: Spring Security + JWT**.
