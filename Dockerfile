# Use a base Java image
FROM openjdk:17-jdk-alpine

# Expose the application's port
EXPOSE 8089

# Copy the JAR from the current directory to the image
COPY target/events-project-1.0.jar events-project-1.0.jar

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/events-project-1.0.jar"]