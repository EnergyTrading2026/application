# Build stage
FROM gradle:9.4.1-jdk21 AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle build.gradle settings.gradle ./
COPY --chown=gradle:gradle gradle /home/gradle/.gradle
COPY --chown=gradle:gradle src /home/gradle/src/src
RUN gradle bootJar

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
