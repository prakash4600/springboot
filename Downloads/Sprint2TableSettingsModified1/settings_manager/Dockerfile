# Use an appropriate base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/*.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
