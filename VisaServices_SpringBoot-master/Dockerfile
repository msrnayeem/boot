# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn clean install

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/VisaServices_SpringBoot-0.0.1-SNAPSHOT.jar /app/VisaServices_SpringBoot-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "VisaServices_SpringBoot-0.0.1-SNAPSHOT.jar"]
