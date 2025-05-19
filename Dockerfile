# Stage 1: Build the app using the Gradle wrapper
FROM gradle:8.4-jdk21 AS builder

WORKDIR /app

# Copy only the build files first (for better caching)
COPY build.gradle settings.gradle gradlew gradlew.bat ./
COPY gradle gradle

# Download Gradle dependencies
RUN ./gradlew --no-daemon build -x test || return 0

# Now copy the full project (including src)
COPY . .

# Build the project again with source files
RUN ./gradlew --no-daemon build -x test

# Stage 2: Runtime
FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=builder /app/build/libs/ExamZenith-0.0.2-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
