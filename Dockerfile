
FROM openjdk:17-jdk


WORKDIR /app


COPY . .


RUN chmod +x mvnw


RUN ./mvnw clean package

EXPOSE 8080


CMD ["java", "-jar", "target/game-service-0.0.1-SNAPSHOT.jar"]
