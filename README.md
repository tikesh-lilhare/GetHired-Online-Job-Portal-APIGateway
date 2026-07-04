## GetHired - Online Job Portal / API Gateway

## 📌 Overview

The API Gateway is the entry point of the AI-Powered Job Portal microservices architecture. All client requests pass through the API Gateway before reaching the backend microservices. The gateway is responsible for request routing, JWT authentication, authorization, and forwarding requests to the appropriate microservice. It communicates with the Eureka Server to dynamically discover available service instances, eliminating the need for hardcoded service URLs.

---

## 🚀 Features
- Centralized API Entry Point
- Request Routing
- JWT Authentication
- JWT Token Validation
- Role-Based Authorization
- Dynamic Routing using Eureka
- Load Balancing
- Route Configuration
- Request Forwarding
- Secure Microservice Communication

--- 

## 🏗 Microservice Architecture



----

## 🛠 Tech Stack
## Backend
- Java 21
- Spring Boot
- Spring Cloud Gateway
- Spring Security
- JWT (JSON Web Token)
- Spring Cloud LoadBalancer
- Spring Cloud Netflix Eureka Client
- Maven

---

## 📦 Maven Dependencies
The project uses the following dependencies:
- Spring Boot Starter WebFlux
- Spring Cloud Gateway
- Spring Security
- Spring Boot Starter Validation
- Spring Cloud Netflix Eureka Client

---

## 📁 Project Structure



---
## application.properties
server.port=8080

spring.application.name=API-GATEWAY

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
