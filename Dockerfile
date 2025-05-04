# Comando para arrancar
ENTRYPOINT ["java","-jar","/app/app.jar"]
# Etapa 1: compilar con Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiamos pom.xml y el wrapper para acelerar la descarga de dependencias
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Descargamos dependencias sin compilar aún
RUN ./mvnw dependency:go-offline -B

# Copiamos el código fuente y compilamos
COPY src ./src
RUN ./mvnw package -DskipTests -B

# Etapa 2: runtime con Java
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiamos el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Arrancamos la aplicación
ENTRYPOINT ["java","-jar","/app/app.jar"]
