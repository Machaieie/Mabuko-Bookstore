
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

1. Clone the repository: `git clone https://github.com/Machaieie/Mabuko-Bookstore`
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


## How to use

### 1. Explore API Endpoints

The application provides several API endpoints for CRUD operations on different entities. Here are the main endpoints:

- **Authors:**
  - List all authors: `GET http://localhost:8080/api/authors`
  - Get an author by ID: `GET http://localhost:8080/api/authors/{id}`
  - Create a new author: `POST http://localhost:8080/api/authors`
  - Update an author: `PUT http://localhost:8080/api/authors/{id}`
  - Delete an author: `DELETE http://localhost:8080/api/authors/{id}`

- **Books:**
  - List all books: `GET http://localhost:8080/api/books`
  - Get a book by ID: `GET http://localhost:8080/api/books/{id}`
  - Create a new book: `POST http://localhost:8080/api/books`
  - Update a book: `PUT http://localhost:8080/api/books/{id}`
  - Delete a book: `DELETE http://localhost:8080/api/books/{id}`
  - Search books by title: `GET http://localhost:8080/api/books/search/title/{title}`
  - Search books by gender: `GET http://localhost:8080/api/books/search/gender/{gender}`

- **Publishers:**
  - List all publishers: `GET http://localhost:8080/api/publishers`
  - Get a publisher by ID: `GET http://localhost:8080/api/publishers/{id}`
  - Create a new publisher: `POST http://localhost:8080/api/publishers`
  - Update a publisher: `PUT http://localhost:8080/api/publishers/{id}`
  - Delete a publisher: `DELETE http://localhost:8080/api/publishers/{id}`

- **Users:**
  - List all users: `GET http://localhost:8080/api/users`
  - Get a user by ID: `GET http://localhost:8080/api/users/{id}`
  - Create a new user: `POST http://localhost:8080/api/users`
  - Update a user: `PUT http://localhost:8080/api/users/{id}`
  - Delete a user: `DELETE http://localhost:8080/api/users/{id}`

- **Clients:**
  - List all clients: `GET http://localhost:8080/api/clients`
  - Get a client by ID: `GET http://localhost:8080/api/clients/{id}`
  - Create a new client: `POST http://localhost:8080/api/clients`
  - Update a client: `PUT http://localhost:8080/api/clients/{id}`
  - Delete a client: `DELETE http://localhost:8080/api/clients/{id}`

### 2. Interact with the Endpoints

- **Using a Web Browser:**
  You can use your web browser to access the provided endpoints. Simply enter the URL into the browser's address bar.

- **Using cURL:**
  You can use cURL commands in the terminal to interact with the API. For example:
  ```bash
  curl -X GET http://localhost:8080/api/authors
  ```

- **Using API Clients:**
  Use API clients like Postman or Insomnia to easily make HTTP requests, inspect responses, and manage your requests.

### 3. Authentication

If your application uses token-based authentication (JWT), you'll need to include the token in the request headers for secured endpoints. Typically, the token is obtained by authenticating with the `/login` endpoint.

Example of obtaining a token:
```bash
curl -X POST \
  http://localhost:8080/login \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "your_username",
    "password": "your_password"
  }'
```

Include the obtained token in subsequent requests:
```bash
curl -X GET \
  http://localhost:8080/api/authors \
  -H 'Authorization: Bearer your_token'
```

### 4. Customize for Your Needs

Feel free to customize the entities, endpoints, and functionalities based on your specific requirements. You can extend the API, add new features, or modify existing ones to meet the needs of your bookstore management system.

This basic guide should give you a starting point to interact with the API. Adjust the URLs and request methods based on your specific use cases and requirements.
## Contributing

Feel free to contribute by submitting issues or pull requests. Contributions are always welcome!


---
