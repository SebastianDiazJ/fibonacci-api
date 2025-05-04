# Usa una imagen ligera de Java 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de la aplicación
WORKDIR /app

# Copia el JAR que genera Maven
COPY target/fibonacci-api-0.0.1-SNAPSHOT.jar app.jar
# Expone el puerto que usará Spring Boot
EXPOSE 8080

# Comando para arrancar
ENTRYPOINT ["java","-jar","/app/app.jar"]
