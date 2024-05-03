# Step 1: Define the base image

FROM openjdk:17-oracle

# Step 2: Set the working directory in the Docker container
WORKDIR /app

# Step 3: Copy the jar file to the working directory
COPY target/desafio-0.0.1-SNAPSHOT.jar /app

# Step 4: Expose the port the app runs on
EXPOSE 8080

# Step 5: Define the command to run the app
ENTRYPOINT ["java", "-jar", "desafio-0.0.1-SNAPSHOT.jar"]
