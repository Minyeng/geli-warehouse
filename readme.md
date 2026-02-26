# 📦 Inventory Management API
A Spring Boot–based Inventory Management System that supports:
- Items
- Variants (size, color, etc.)
- Pricing per variant
- Stock tracking per variant
- Clean Architecture structure
## 📚 Libraries & Dependencies
This project is built using the following core libraries and frameworks:
1. Spring Boot 3+
2. Spring Web
3. Spring Data JPA
4. Validation
5. PostgreSQL Driver
6. Lombok
7. SpringDoc OpenAPI (for OpenAPI / Swagger documentation)
# 🚀 How to Run the Application
## 1️⃣ Requirements
- Java 17+
- Maven 3.9+
- PostgreSQL
- Git
## 2️⃣ Clone the Project
```bash
git clone https://github.com/Minyeng/geli-warehouse.git
cd geli-warehouse
```
## 3️⃣ Configure Database
```code
src/main/resources/application.yml
```
Example:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/your_db
    username: your_user_db
    password: your_pass_db
    driver-class-name: org.postgresql.Driver
  jpa:
    show-sql: true
    properties:
      hibernate:
        '[format_sql]': true
        dialect: org.hibernate.dialect.PostgreSQLDialect
  sql:
    init:
      mode: always
```
Create database manually:
```sql
CREATE DATABASE your_db;
```
## 4️⃣ Run the Application
Using mave:
```bash
mvn spring-boot:run
```
Or:
```bash
mvn clean install
java -jar target/warehouse-0.0.1-SNAPSHOT.jar
```
Application will run at:
```code
http://localhost:8080
```
# 📘 API Documentation (Swagger)
This project uses Springdoc OpenAPI (Swagger UI) for interactive API documentation.
## 🔎 Access Swagger UI
After running the application, open:
``` code
http://localhost:8080/swagger-ui.html
```
**[👉 Click here 👈](http://localhost:8080/swagger-ui.html) to view API documentation**
# 🗄 Database Initialization & Migration
## 🔄 Automatic Migration on Application Startup
When the application starts, the database schema and seed data are automatically initialized.
Spring Boot executes SQL files located in:
``` plaintext
src/main/resources/
 ├── schema.sql
 └── data.sql
```
## 🏗 Database Design Overview
The database follows a normalized relational structure with a clear separation between:
- Items (Products)
- Variants (Variant categories & names)
- Item Variants (Product Stocks)
- Item Variant Mapping (to support multiple variant attributes for product stock)
# 🏗 Architecture & Design Decisions
## 1️⃣ Architecture Style
This project follows:
- Layered Clean Architecture
- Separation of concerns
Structure:
```code
com.inventory
 ├── domain
 │    ├── model
 │    └── repository
 ├── application
 │    └── service
 ├── infrastructure
 │    └── persistence
 │    │    ├── jpa
 │    │    │    ├── entity
 │    │    │    └── repository
 │    │    ├── mapper
 │    │    └── repository (for domain repositories implementation) 
 └── presentation
      ├── controller
      ├── request
      └── response
```
