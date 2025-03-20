# Використовуємо образ Java 17
FROM openjdk:17-jdk

# Налаштовуємо робочу директорію
WORKDIR /app

# Копіюємо файли проєкту у контейнер
COPY . .

# Надаємо mvnw права на виконання
RUN chmod +x mvnw

# Збираємо проєкт за допомогою Maven Wrapper
RUN ./mvnw clean package

# Вказуємо команду для запуску додатку
CMD ["java", "-jar", "target/game-service-0.0.1-SNAPSHOT.jar"]
