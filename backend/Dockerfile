# Build stage
FROM gradle:9.4.1-jdk21 AS build
WORKDIR /home/gradle/src
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY src ./src
RUN gradle bootJar

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
