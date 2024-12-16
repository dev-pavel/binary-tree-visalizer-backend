# Use a base image for Java
FROM eclipse-temurin:22-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Maven/Gradle build files and application JAR
COPY target/binary-tree-visalizer-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
