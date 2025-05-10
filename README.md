# Project Description

In a simple account app using Spring Security, authentication means checking if a user’s login (like email and password) is correct. Once the user logs in, the system creates a token (like a JWT) that the user sends with every request. Authorization means checking if the logged-in user has the right role (like ROLE_ADMIN or ROLE_USER) to access certain parts of the app. You protect routes with rules like hasRole("ROLE_ADMIN"), and Spring Security checks the token and roles before allowing access.

## 🚀 Features

- Login & registration endpoints

- Passwords hashed

- JWT token generation and validation

- Role-based access control

- Secure APIs using filters and annotations

- Custom UserDetailsService to load users from database


## 🛠️ Tech Stack

List of technologies/frameworks used:

- Java / Spring Boot – Application framework
- Spring Security – For authentication & authorization
- JWT (JSON Web Token) – For stateless session management
- Spring Data JPA – For database access
- PostgreSQL – As database options
- Docker
- BCrypt – For password hashing

## 📦 Installation

### Prerequisites

- Java 24
- Maven
- Docker
- PostgreSQL
- Postman

### Steps
📦 Add the following dependencies:

- Spring Web
- Spring Security
- Spring Data JPA
- PostgreSQL Driver
- Spring Boot DevTools
- JWT library
- Validation (optional for request validations)

⚙️`application.properties` Configuration

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_user
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
🐘 PostgreSQL with Docker
```
version: "3.8"
services:
  postgres:
    image: postgres:15
    container_name: springboot_postgres
    environment:
      POSTGRES_DB: your_db
      POSTGRES_USER: your_user
      POSTGRES_PASSWORD: your_password
    ports:
      - "5432:5432"
    volumes:
      - postgres-data:/var/lib/postgresql/data

volumes:
  postgres-data:
```
Run it using:
```
docker-compose up -d
```

🔐 BCrypt for Password Hashing
```
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```
🔧 JWT Setup
- JWT Utility class (for token creation/validation)
- Custom UserDetailsService
- SecurityFilterChain bean with HTTP security config
- AuthenticationManager bean

To follow and understand all the implementation, here is the link to follow:  
<a href="https://www.tutorialspoint.com/spring_security/spring_security_introduction.htm" target="_blank">Spring Security Introduction - TutorialsPoint</a>
