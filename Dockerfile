# Use a lightweight JRE image for running the application
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the repackaged executable JAR file into the container
COPY target/ShoppingCartBackend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port matching server.port in application.properties
EXPOSE 5050

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
