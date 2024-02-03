# chat-app


The CHAT-APP is a simple chat application that allows users to send messages to each other.

It is a Spring Boot application that uses Hibernate JPA to store messages in a PostgreSQL database, aided by Flyway

It features an Angular GUI

The containerization via Docker is not yet working. The Dockerfile and docker-compose.yml are there, but the application does not start up correctly.

In order to run, you need to have a PostgreSQL database running on localhost:5432 with a database called "chat-app"

Flyway will create the necessary tables

To run: execute com.joel.chat.app.Starter

Swagger: http://localhost:8080/swagger-ui/index.html