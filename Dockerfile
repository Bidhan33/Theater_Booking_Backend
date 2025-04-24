# Use Eclipse Temurin Java 17 JDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy built JAR file into the container
COPY target/movie-0.0.1-SNAPSHOT.jar app.jar

# Expose your application's port (adjust if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
