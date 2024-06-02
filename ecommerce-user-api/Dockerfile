FROM openjdk:17-alpine
VOLUME /tmp
ARG JARFILE=target/user-api-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]