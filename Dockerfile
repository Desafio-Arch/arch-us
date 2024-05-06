# Step 1: Define the base image
FROM maven:3.8.4-openjdk-17 as build

# Step 2: Set the working directory in the Docker container
WORKDIR /app

# Step 3: Copy the pom.xml file to the working directory
COPY pom.xml .

# Step 4: Copy the source code to the working directory
COPY src ./src

# Step 5: Build the project
RUN mvn clean install

# Step 6: Define the final image
FROM openjdk:17-oracle

# Step 7: Set the working directory in the Docker container
WORKDIR /app

# Step 8: Copy the jar file from the build image to the final image
COPY --from=build /app/target/desafio-0.0.1-SNAPSHOT.jar .

# Step 9: Expose the port the app runs on
EXPOSE 8080

# Step 10: Define the command to run the app
ENTRYPOINT ["java", "-jar", "desafio-0.0.1-SNAPSHOT.jar"]