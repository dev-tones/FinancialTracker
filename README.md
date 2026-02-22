# 📘 Financial Tracker – Full Stack App (Spring Boot + React)

Financial Tracker is a personal finance application designed to help users track expenses, income, and categories while learning clean full-stack architecture.

This project is being built step-by-step to understand:

- Spring Boot best practices
- DTO → Service → Repository → Entity layering
- REST API design
- React frontend using Vite + TypeScript
- Clean project folder structure

---

## 🚀 Tech Stack

Backend:

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 (in-memory dev database)
- Maven

Frontend:

- React
- Vite
- TypeScript

---

## 📂 Project Structure

    spring-react/
    │
    ├── README.md
    ├── backend/
    │   └── src/main/java/com/twigg/backend/
    │       ├── controller/
    │       ├── dto/
    │       ├── model/
    │       ├── repository/
    │       ├── service/
    │       └── BackendApplication.java
    │
    └── frontend/
        ├── src/
        └── vite.config.ts

---

## ⚙️ Backend Architecture Overview

    Controller → Service → Repository → Entity → Database
            ↑                                   ↓
      Request DTO                         Response DTO

DTOs:

- CreateTransactionRequest
- TransactionResponse

Entity:

- Transaction

Repository:

- TransactionRepository (extends JpaRepository<Transaction, Long>)

Service:

- TransactionService (interface)
- TransactionServiceImpl (implementation)

Controller:

- REST endpoints for POST and GET operations

---

## 🗄️ Database

Development Database: H2 In-Memory

- Automatically configured by Spring Boot
- Resets on every restart

Context path (from application.properties):

    server.servlet.context-path=/api/v1

All routes are prefixed with /api/v1.

---

## 📡 API Endpoints

Base URL:

    http://localhost:8080/api/v1/transaction

---

### POST /api/v1/transaction

Create a new transaction.

Request JSON:

    {
      "date": "2026-02-21",
      "amount": 42.75,
      "type": "EXPENSE",
      "categoryId": 3,
      "description": "Groceries from Kroger"
    }

Example Response:

    {
      "id": 1,
      "date": "2026-02-21",
      "amount": 42.75,
      "type": "EXPENSE",
      "categoryId": 3,
      "description": "Groceries from Kroger"
    }

---

### GET /api/v1/transaction

Retrieve all transactions.

Example Response:

    [
      {
        "id": 1,
        "date": "2026-02-21",
        "amount": 42.75,
        "type": "EXPENSE",
        "categoryId": 3,
        "description": "Groceries from Kroger"
      }
    ]

If none exist:

    []

---

## ▶️ Running the Backend

From the backend folder:

    mvn spring-boot:run

Backend will run at:

    http://localhost:8080

API located at:

    http://localhost:8080/api/v1/transaction

---

## ▶️ Running the Frontend

From the frontend folder:

    npm install
    npm run dev

Frontend will run at:

    http://localhost:5173

---

## 🧭 Current Features

Backend:

- Create new transaction (POST)
- Retrieve all transactions (GET)
- DTO mapping
- Clean service/repository layering
- In-memory DB

Frontend:

- React + Vite scaffolded
- Ready for integration

---

## 🛠️ Planned Enhancements

Backend:

- Get transaction by ID
- Delete transaction
- Update transaction
- Add Category entity + CRUD
- Add budget and monthly summaries
- Move to Postgres/MySQL using Docker

Frontend:

- Transaction creation form
- Display transaction list/table
- Category dropdown
- Charts and graphs
- State management

---

## 🧑‍💻 Author Notes

This project is being built intentionally, one layer at a time, to deeply understand:

- Spring Boot architecture
- REST API design
- DTO patterns
- Full-stack integration

Every backend layer was written manually for real understanding.
