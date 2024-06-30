# Use the OpenJDK 17 image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Argument for the JAR file to be copied
ARG JAR_FILE=target/*.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Set environment variables if needed
ENV SPRING_GRAPHQL_GRAPHIQL_ENABLED=true

# Expose the port that your Spring Boot application runs on
EXPOSE 8080

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]