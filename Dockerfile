FROM openjdk:17
WORKDIR /app
COPY target/ms_content_moderator-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
