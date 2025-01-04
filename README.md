# Chatterly

Chatterly is a ManyChat clone designed to provide an interactive chatbot experience for automating customer interactions and message flows. This project is a full-stack microservices application built to explore and learn modern microservice architecture.

## Project Structure

```
/
├── client           # Frontend (Next.js)
│   └── Runs on port 3000
├── server
│   ├── auth_service # Authentication Service (Spring Boot)
│   │   └── Runs on port 8081
│   ├── api_gateway  # API Gateway (Spring Boot)
│       └── Runs on port 8080
└── docker-compose.yaml # Services for Postgres, PgAdmin, RabbitMQ, Redis
```

## Features

- **Frontend**: A Next.js application for user interaction.
- **Backend**:
  - **Auth Service**: Handles user authentication and token management.
  - **API Gateway**: Acts as a central point for routing requests to appropriate microservices.
- **Database**: PostgreSQL for persistent storage.
- **Caching**: Redis for session management and caching.
- **Message Broker**: RabbitMQ for asynchronous communication between microservices.
- **Containerization**: Docker for service orchestration.
- **Production Deployment**: Kubernetes (K8s) for backend services in production.

## Technologies Used

### Frontend

- Next.js
- React

### Backend

- Spring Boot
- JWT Authentication
- PostgreSQL
- Redis
- RabbitMQ
- Docker
- Kubernetes (for production)

## Setup Instructions

### Prerequisites

Ensure you have the following installed on your machine:

- Node.js (v16 or higher)
- Docker and Docker Compose
- Java (JDK 17 or higher)
- Maven
- Kubernetes CLI (kubectl) for production deployment

### Local Development Setup

#### 1. Clone the Repository

```bash
git clone https://github.com/ritikjee/chatterly.git
cd chatterly
```

#### 2. Backend Setup

Navigate to the `server` directory and set up the backend services.

##### Step 1: Start Docker Services

Run the Docker Compose file to start PostgreSQL, PgAdmin, RabbitMQ, and Redis.

```bash
cd server
docker-compose up -d
```

##### Step 2: Start Backend Services

- **Auth Service**:
  ```bash
  cd auth_service
  mvn spring-boot:run
  ```
- **API Gateway**:
  ```bash
  cd api_gateway
  mvn spring-boot:run
  ```

#### 3. Frontend Setup

Navigate to the `client` directory and set up the Next.js application.

```bash
cd client
npm install
npm run dev
```

The client will run on [http://localhost:3000](http://localhost:3000).

#### 4. Verify Setup

- Auth Service: [http://localhost:8081](http://localhost:8081)
- API Gateway: [http://localhost:8080](http://localhost:8080)
- Frontend: [http://localhost:3000](http://localhost:3000)

### Production Deployment

For production, the backend services will be deployed to Kubernetes.

#### 1. Build Docker Images

Create Docker images for the backend services.

```bash
cd auth_service
mvn spring-boot:build-image
```

Repeat the same for `api_gateway`.

#### 2. Deploy to Kubernetes

Use `kubectl` or a CI/CD pipeline to deploy your services to your Kubernetes cluster. Ensure you configure secrets and environment variables for production use.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch.
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes.
   ```bash
   git commit -m "Add feature name"
   ```
4. Push your changes.
   ```bash
   git push origin feature-name
   ```
5. Create a pull request.

Your contributions, whether big or small, make a difference in improving this project. Bug reports, feature requests, and suggestions are highly encouraged.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

---

### Repository Link

[Chatterly on GitHub](https://github.com/ritikjee/chatterly)
