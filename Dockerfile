FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY build/libs/publish-management-pro2-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
