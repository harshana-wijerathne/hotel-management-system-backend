FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]