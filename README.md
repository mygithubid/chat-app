# chat-app


The CHAT-APP is a simple chat application that allows users to send messages to each other.

It is a Spring Boot application that uses Hibernate JPA to store messages in a PostgreSQL database, aided by Flyway

It features an Angular GUI

jar:
    ./mvnw package -DskipTests

build:
    docker compose build

up:
    docker compose up

Flyway will create the necessary tables in the database

Swagger: http://localhost:8080/swagger-ui/index.html