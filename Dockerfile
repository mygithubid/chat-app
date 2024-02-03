FROM openjdk:17
MAINTAINER joel.com
COPY target/chat-app-0.0.1-SNAPSHOT.jar chat-app.jar
ENTRYPOINT ["java","-jar","/chat-app.jar"]