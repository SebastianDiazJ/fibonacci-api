FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests

CMD ["java", "-jar", "target/fibonacci-api-0.0.1-SNAPSHOT.jar"]
