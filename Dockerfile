# Use the official OpenJDK 17 image as a base image
FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/clever-climb-0.0.1-SNAPSHOT.jar clever-climb.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "clever-climb.jar"]
