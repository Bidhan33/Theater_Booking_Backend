# Movie Theater Management System API

A Spring Boot application for managing movies, shows, theaters, tickets, and user authentication in a movie theater system.

## Features

- **User Authentication**: JWT-based authentication with role-based authorization
- **Movie Management**: Add, delete, and list movies (admin-protected operations)
- **Show Management**: Create shows, associate seats, and delete shows
- **Theater Management**: Add theaters and theater seats
- **Ticket Booking**: Book and cancel tickets

---

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


##  
{
  "status": 201,
  "message": "User added successfully"
}

##
