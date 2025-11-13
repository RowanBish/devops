# Use the latest OpenJDK image
FROM openjdk:latest

# Copy the new fat JAR into the container
COPY ./target/devops-0.1.0.2-alpha-2-jar-with-dependencies.jar /app/app.jar

# Set working directory
WORKDIR /app

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]