FROM eclipse-temurin:17-jdk-jammy
# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file (update with your JAR file name if needed)
COPY target/backend-0.0.0.jar app.jar

# Expose port (default for Spring Boot)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]