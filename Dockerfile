
FROM openjdk:17-jdk


WORKDIR /app


COPY . .


RUN ./mvnw clean package


CMD ["java", "-jar", "target/game-service-0.0.1-SNAPSHOT.jar"]
