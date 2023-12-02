
# Livraria Mabuko Project

## Overview

This project is a simple book management system for a bookstore, implementing entities such as Author, Book, Publisher, User, and Client. It provides basic CRUD (Create, Read, Update, Delete) operations for managing books and associated entities. The project uses MySQL for the database, incorporates validation for input data, utilizes Lombok for simplified Java code, and includes Spring Security for user authentication and authorization using tokens.

## Entities

### 1. Author

- Represents an author of a book.
- Fields:
  - `id` (auto-generated unique identifier)
  - `name` (name of the author)
  - `bibliography` (author's bibliography)
  - `nationairy` (nationality of the author)
  - `books` (list of books associated with the author)

### 2. Book

- Represents a book in the bookstore.
- Fields:
  - `id` (auto-generated unique identifier)
  - `title` (title of the book)
  - `publisherDate` (date when the book was published)
  - `edition` (edition number of the book)
  - `gender` (genre of the book)
  - `availability` (availability status of the book)
  - `author` (reference to the author of the book)
  - `publisher` (reference to the publisher of the book)

### 3. Publisher

- Represents a publisher of books.
- Fields:
  - `id` (auto-generated unique identifier)
  - `name` (name of the publisher)
  - `location` (location of the publisher)
  - `phone` (phone number of the publisher)
  - `books` (list of books associated with the publisher)

### 4. User

- Represents a user of the system.
- Fields:
  - `id` (auto-generated unique identifier)
  - `name` (name of the user)
  - `username` (username of the user)
  - `password` (hashed password of the user)
  - `role` (role of the user)

### 5. Client

- Represents a client of the bookstore.
- Fields:
  - `id` (auto-generated unique identifier)
  - `name` (name of the client)
  - `phone` (phone number of the client)
  - `email` (email address of the client)
  - `address` (address of the client)

## Project Structure

The project follows a standard Spring Boot project structure. Key packages include:

- `com.livrariamabuko.Livraria.Mabuko.model`: Contains the entity classes (Author, Book, Publisher, User, Client).
- `com.livrariamabuko.Livraria.Mabuko.repository`: Contains the Spring Data JPA repositories for database interactions.
- `com.livrariamabuko.Livraria.Mabuko.service`: Contains the service classes for business logic.
- `com.livrariamabuko.Livraria.Mabuko.controller`: Contains the controllers for handling HTTP requests.
- `com.livrariamabuko.Livraria.Mabuko.exceptions`: Contains custom exception classes.
- `com.livrariamabuko.Livraria.Mabuko.security`: Contains configurations for Spring Security.

## Authentication and Authorization

User authentication and authorization are implemented using token-based security. JWT (JSON Web Tokens) or a similar token mechanism is employed for secure user access.

## Getting Started

To run the project locally, follow these steps:

1. Clone the repository: `git clone <repository_url>`
2. Navigate to the project directory: `cd Livraria-Mabuko`
3. Update `application.properties` with your MySQL database configuration.
4. Run the application: `./mvnw spring-boot:run`

The application will start, and you can access it at `http://localhost:8080`.

## API Endpoints

- `/api/authors`: CRUD operations for authors.
- `/api/books`: CRUD operations for books.
- `/api/publishers`: CRUD operations for publishers.
- `/api/users`: CRUD operations for users.
- `/api/clients`: CRUD operations for clients.

## Dependencies

- Java 8 or higher
- Spring Boot
- Spring Data JPA
- Spring Web
- H2 Database (for development, you can configure MySQL for production)
- Lombok
- Spring Security
- MySQL

## Contributing

Feel free to contribute by submitting issues or pull requests. Contributions are always welcome!


---
