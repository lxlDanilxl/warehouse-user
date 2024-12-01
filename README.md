# User Microservice

The User Microservice is a component of our system designed to manage user-related functionalities such as registration, authentication, and user profile management.

## Features

### User Features

- **User Registration**: Allows new users to sign up by providing necessary details.
- **User Authentication**: Supports login functionality using username and password.
- **Email Change**: Enables users to update their email.
- **Password Change**:Enables users to update their password.

### Role Features

- **CRUD Operations**: All CRUD operations for role managing.

## Technologies Used

- **Spring Boot**: Framework for building the microservice.
- **Spring Security**: Provides authentication and authorization capabilities.
- **JPA/Hibernate**: For database interactions.
- **JWT**: JSON Web Tokens for secure authentication.
- **Docker Compose**: For containerized deployments.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker and Docker Compose

### Running the Microservice

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd user
   ```

2. Build the application:

   ```bash
   ./mvnw clean package
   ```

3. Run using Docker Compose:

   ```bash
   docker-compose up
   ```

### API Endpoints

- `POST /api/register`: Register a new user.
- `POST /api/login`: Authenticate a user and receive a JWT.
- `GET /api/profile`: Retrieve the user profile (secured endpoint).
- `PUT /api/profile`: Update the user profile (secured endpoint).

### Security

- JWT-based authentication.
- Role-based access control for endpoints.
