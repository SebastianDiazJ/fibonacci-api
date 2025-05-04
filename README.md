# Fibonacci API

## Descripción

La **Fibonacci API** es una aplicación desarrollada en **Java** utilizando el framework **Spring Boot**. Su propósito principal es procesar secuencias de números de Fibonacci,
almacenarlas en una base de datos de postgrest y enviar los resultados por correo electrónico.
La API permite a los usuarios cargar un archivo con los parámetros necesarios para generar la secuencia de Fibonacci
y automatiza el procesamiento, almacenamiento y envío de los resultados.

## Funcionalidades

1. **Carga de Archivos**:
   - Los usuarios pueden cargar un archivo en formato de texto plano (`.txt`) que contiene los parámetros necesarios para generar la secuencia de Fibonacci.
   - El archivo debe tener el siguiente formato:
     ```
     <número_inicial_1>,<número_inicial_2>,<cantidad_de_elementos>,<correo_electrónico>
     ```
     Ejemplo:
     ```
     0,1,10,usuario@ejemplo.com
     ```

2. **Generación de Secuencia de Fibonacci**:
   - A partir de los dos números iniciales y la cantidad de elementos especificados, la API genera la secuencia de Fibonacci.

3. **Almacenamiento en Base de Datos**:
   - Los resultados generados se almacenan en una base de datos utilizando **Spring Data JPA**. Cada resultado incluye:
     - Los números iniciales.
     - La cantidad de elementos generados.
     - La secuencia completa.
     - La fecha de procesamiento.
     - El correo electrónico del destinatario.

4. **Envío de Resultados por Correo Electrónico**:
   - Una vez procesada la secuencia, la API envía un correo electrónico al destinatario especificado con los resultados y la fecha de envío.

## Endpoints

### `POST /api/file/upload`
- **Descripción**: Permite cargar un archivo con los parámetros para generar la secuencia de Fibonacci.
- **Parámetros**:
  - `file` (MultipartFile): Archivo que contiene los parámetros.
- **Respuesta**:
  - `200 OK`: Si el archivo se procesa correctamente.
  - `400 BAD REQUEST`: Si ocurre un error al procesar el archivo.
- **Ejemplo de Respuesta Exitosa**:
  ```json
  {
    "message": "Archivo procesado correctamente"
  }
  ```

## Tecnologías Utilizadas

- **Lenguaje**: Java
- **Framework**: Spring Boot
- **Gestión de Dependencias**: Maven
- **Base de Datos**: JPA (Hibernate)
- **Correo Electrónico**: Spring Mail
- **Servidor Web**: Tomcat (embebido)

## Estructura del Proyecto

- `controller`: Contiene los controladores REST para manejar las solicitudes HTTP.
- `service`: Implementa la lógica de negocio, como el cálculo de la secuencia de Fibonacci y el envío de correos electrónicos.
- `repository`: Gestiona la interacción con la base de datos.
- `model`: Define las entidades utilizadas en la base de datos.

## Requisitos Previos

1. **Java 17 o superior**.
2. **Maven** para la gestión de dependencias.
3. Configuración de un servidor SMTP para el envío de correos electrónicos.

## Ejecución

1. Clonar el repositorio.
2. Configurar las credenciales del servidor SMTP en el archivo `application.properties`:
   ```properties
   spring.mail.host=smtp.ejemplo.com
   spring.mail.port=587
   spring.mail.username=tu_correo@ejemplo.com
   spring.mail.password=tu_contraseña
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true
   ```
3. Ejecutar el proyecto con Maven:
   ```bash
   mvn spring-boot:run
   ```
4. Acceder a la API en `http://localhost:8080/api/file/upload`.

## Notas

- Asegúrate de que el archivo cargado tenga el formato correcto para evitar errores.
- La API está diseñada para manejar excepciones comunes, como errores de formato en el archivo o problemas de conexión con el servidor SMTP.

## Autor

Desarrollado por el equipo de **Sebastian Diaz**.
