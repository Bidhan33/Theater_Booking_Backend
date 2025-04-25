# Movie Theater Management System API

A Spring Boot application for managing movies, shows, theaters, tickets, and user authentication in a movie theater system.

## Features

- **User Authentication**: JWT-based authentication with role-based authorization
- **Movie Management**: Add, delete, and list movies (admin-protected operations)
- **Show Management**: Create shows, associate seats, and delete shows
- **Theater Management**: Add theaters and theater seats
- **Ticket Booking**: Book and cancel tickets

## API Endpoints

### Authentication

#### Register New User
- `POST /user/addNew`
- Request:
  ```java
  {
    "name": "string",
    "emailId": "string@example.com",
    "password": "string",
    "roles": "USER" or "ADMIN"
  }
Success Response:

json
{
  "status": 201,
  "message": "User added successfully"
}
Authenticate User
POST /user/getToken

Request:

java
{
  "username": "string@example.com",
  "password": "string"
}
Success Response:

json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
Movies
Add New Movie (Admin only)
POST /movie/addNew

Headers: Authorization: Bearer <token>

Request:

java
{
  "title": "string",
  "description": "string",
  "duration": 120,
  "language": "string",
  "releaseDate": "yyyy-MM-dd",
  "genre": "string"
}
Success Response:

json
{
  "status": 201,
  "message": "Movie added successfully"
}
Delete Movie (Admin only)
DELETE /movie/delete/{movieId}

Headers: Authorization: Bearer <token>

Success Response:

json
{
  "status": 200,
  "message": "Movie deleted successfully"
}
Get All Movies
GET /movie/getAll

Success Response:

json
[
  {
    "id": 1,
    "title": "Inception",
    "description": "A thief who steals corporate secrets...",
    "duration": 148,
    "language": "English",
    "releaseDate": "2010-07-16",
    "genre": "Sci-Fi"
  }
]
Shows
Add New Show
POST /show/addNew

Request:

java
{
  "showDate": "yyyy-MM-dd",
  "showTime": "HH:mm:ss",
  "movieId": 1,
  "theaterId": 1
}
Success Response:

json
{
  "status": 201,
  "message": "Show added successfully"
}
Associate Seats to Show
POST /show/associateSeats

Request:

java
{
  "showId": 1,
  "seatIds": [1, 2, 3]
}
Success Response:

json
{
  "status": 201,
  "message": "Seats associated successfully"
}
Delete Show
DELETE /show/delete/{id}

Success Response:

json
{
  "status": 200,
  "message": "Show deleted successfully"
}
Theaters
Add New Theater
POST /theater/addNew

Request:

java
{
  "name": "string",
  "location": "string",
  "capacity": 100
}
Success Response:

json
{
  "status": 201,
  "message": "Theater added successfully"
}
Add Theater Seats
POST /theater/addTheaterSeat

Request:

java
{
  "theaterId": 1,
  "seatNumbers": ["A1", "A2", "B1"],
  "seatTypes": ["PREMIUM", "REGULAR", "VIP"]
}
Success Response:

json
{
  "status": 201,
  "message": "Seats added successfully"
}
Tickets
Book Ticket
POST /ticket/book

Request:

java
{
  "showId": 1,
  "userId": 1,
  "seatIds": [1, 2],
  "paymentMethod": "CREDIT_CARD"
}
Success Response:

json
{
  "ticketId": 1,
  "showDetails": {
    "movieTitle": "Inception",
    "showTime": "2023-12-25T18:00:00"
  },
  "seats": ["A1", "A2"],
  "totalAmount": 24.50,
  "bookingDate": "2023-12-20T14:30:00"
}
Cancel Ticket
DELETE /ticket/delete/{ticketId}

Success Response:

json
{
  "status": 200,
  "message": "Ticket cancelled successfully"
}
Database Schema Overview
User
- id (Long)
- name (String)
- emailId (String)
- password (String)
- roles (String)

Movie
- id (Long)
- title (String)
- description (String)
- duration (Integer)
- language (String)
- releaseDate (Date)
- genre (String)

Theater
- id (Long)
- name (String)
- location (String)
- capacity (Integer)

TheaterSeat
- id (Long)
- theaterId (Long)
- seatNumber (String)
- seatType (String)

Show
- id (Long)
- showDate (Date)
- showTime (Time)
- movieId (Long)
- theaterId (Long)

ShowSeat
- id (Long)
- showId (Long)
- seatId (Long)
- status (String)

Ticket
- id (Long)
- showId (Long)
- userId (Long)
- bookingDate (DateTime)
- paymentMethod (String)
Security Configuration
JWT token expiration: 24 hours

Password encryption: BCrypt

Protected endpoints require Authorization: Bearer <token> header

Admin-only endpoints have @PreAuthorize("hasRole('ADMIN')")

Error Handling
Common error responses:

json
{
  "status": 400,
  "message": "Invalid request parameters"
}

{
  "status": 401,
  "message": "Unauthorized: Invalid credentials"
}

{
  "status": 403,
  "message": "Forbidden: Insufficient permissions"
}

{
  "status": 404,
  "message": "Resource not found"
}

{
  "status": 500,
  "message": "Internal server error"
}
Setup Instructions
Clone the repository

Configure database in application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/movie_db
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
Build the project: mvn clean install

Run the application: mvn spring-boot:run

Access Swagger UI at: http://localhost:8080/swagger-ui.html (if configured)

Dependencies
Spring Boot 2.7+

Spring Security

JWT (jjwt)

Lombok

MySQL Driver

Spring Data JPA

Testing
Sample test cases:

User registration -> authentication -> movie listing

Admin adds movie -> creates show -> associates seats

User books ticket -> views booking -> cancels ticket

Use Postman collection or automated tests with:

JUnit

MockMvc

TestContainers (for integration tests)

