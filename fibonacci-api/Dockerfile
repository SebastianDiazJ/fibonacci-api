# Etapa 1: compilar con Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiamos sólo lo necesario para descargar dependencias
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn/

# Pre-descargamos dependencias para acelerar el build incremental
RUN ./mvnw dependency:go-offline -B

# Copiamos el código fuente y compilamos
COPY src ./src
RUN ./mvnw package -DskipTests -B

# Etapa 2: runtime con Java
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiamos el JAR construido en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto de la aplicación
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

