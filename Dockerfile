FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests
# The actual JAR name might include the version (e.g., backend-0.0.0.jar)
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]