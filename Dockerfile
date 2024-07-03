#FROM maven:3.9.0 AS builder
#WORKDIR /app
#COPY . .
#RUN mvn clean install -DskipTests

FROM openjdk:17
WORKDIR /app
COPY target/*.jar app.jar
#COPY --from=builder /app/target/*.jar app.jar


ENV PORT ${PORT}
ENV DATABASE_URL ${DATABASE_URL}
ENV DATABASE_USERNAME ${DATABASE_USERNAME}
ENV DATABASE_PASSWORD ${DATABASE_PASSWORD}

EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
