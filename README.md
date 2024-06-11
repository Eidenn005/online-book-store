# Online Book Store

## Overview
An online book store application built using Java. 
This application allows users to browse, search, and purchase books online.
The project demonstrates a typical e-commerce platform's backend functionalities, including user management, book inventory, and order processing.

## Technologies and tools
This project utilizes a variety of technologies and tools to ensure
a robust, scalable, and maintainable car sharing service.
Below is an overview of the main technologies and tools used:
### Backend:
- **Java**: The primary programming language used for the backend services.
- **Spring Boot**: A powerful framework for building production-ready applications quickly,
  providing features such as dependency injection, web frameworks, data access, and security.
- **Spring Security**: Used to handle authentication and authorization.
- **Spring Data JPA**: Simplifies data access and management using Java Persistence Api.
- **Liquibase**: An open-source library for tracking, managing, and applying database schema changes.
### Database:
- **MySql**: A widely-used open-source relational database system, chosen for its reliability,
  ease of use, and support for complex queries and transactions.
### Testing:
- **JUnit**: A widely used testing framework for Java applications.
- **Mockito**: A mocking framework for unit test in Java.
- **Spring Boot Test**: Provide utilities for testing Spring Boot applications.
### CI/CD:
- **GitHub Actions**: Provides CI capabilities to automate building and testing the application.
### Containerization:
- **Docker**: Used to create, deploy, and run application in containers.
- **Docker Compose**: Used for defining and running multi-container Docker application.
### API Documentation:
- **Swagger/OpenApi**: Tools for generating and visualizing API documentation, making it easier to understand and use the API endpoints.
### Code Quantity:
- **Checkstyle**: A development tool to help ensure that Java code adheres a coding standard.
- **Maven**: A build automation tool used primary for Java projects. It simplifies the build process and dependency management.
### Development Environment:
- **IntelliJIdea**: An integrated development environment for Java development.
- **Postman**: A tool for testing APIs by sending request and receiving response.

## Feature and functionality:
### User management:
**Registration**: Users can register on the platform by providing their email, 
first name, last name, and password.
```bash
   POST: api/auth/register
   ``` 
**Authentication**: Secure login functionality using JWT tokens to manage sessions.
Users can log in using their credentials (email and password).
```bash
   POST: api/auth/login
   ```
### Book management:
**Searching** All users, including unauthenticated users, can view list of all books.
```bash
   GET: api/books/
   ```
**Searching by book ID** All users, including unauthenticated users, can find book by it ID.
```bash
   GET: api/books/{id}
   ```

**Add book** Admins can add book to inventory
```bash
   POST: api/books
   ```

**Update books** Admins can update book by ID
```bash
   PUT: api/books/{id}
   ```

**Delete books** Admins can delete book by ID
```bash
   DELETE: api/books/{id}
   ```

### Shopping cart management:
**Add item** Users can add item to shopping cart
```bash
   POST: api/cart
   ```

**Get items** Users can view items in shopping cart
```bash
   GET: api/cart
   ```

**Update items** Users can update items in shopping cart
```bash
   PUT: api/cart/cart-items/{id}
   ```

**Delete items** Users can delete items from their shopping cart
```bash
   DELETE: api/cart/cart-items/{id}
   ```

### Order management:
//TODO
**Place an order** Users can place an order
```bash
   POST: api/orders
   ```

**Get orders history** Users can get history of their orders
```bash
   GET: api/orders
   ```

**Get specific order by it ID** Users can get order by it ID
```bash
   GET: api/orders/{orderId}
   ```

**Get specific item info from order** Users can get item info from order by it ID
```bash
   GET: api/orders/{orderId}/items/{itemId}
   ```

**Update order status** Admins can update order status by it ID
```bash
   PATCH: api/orders/{orderId}
   ```

### Category management:
//TODO

## Installation
//TODO

## Installation
### Pre requirements:
- **Java Development Kit (JDK 21) or higher**
- **Maven**: Install Maven for building the project.
- **MySQL**: Install MySQL and create a database for the project.
- //TODO docker and CI
### Steps:
//TODO