# 🧮 Fibonacci API (Versión basada en hora del sistema)

## 📌 Descripción

Esta API genera una secuencia descendente de Fibonacci **utilizando los minutos (X)** y **segundos (Y)** de una hora específica (formato `HH:mm:ss`) como semillas.

👉 La API fue desarrollada con **Java 17** y **Spring Boot**, implementando buenas prácticas de arquitectura (MVC), pruebas automatizadas, autenticación básica, envío de correos electrónicos y despliegue en Render.

---

## ⚙️ Funcionalidades

✅ Genera secuencias de Fibonacci **descendentes**  
✅ Toma la hora del sistema 
✅ Guarda los resultados en una base de datos PostgreSQL  
✅ Envía el resultado por **correo electrónico a dos destinatarios**  
✅ Permite consultar el historial de resultados por endpoint autenticado  
✅ Contiene pruebas unitarias y de integración  
✅ Expuesta visualmente con endpoints en Swagger UI  
✅ Desplegada en Render para uso público

---

## 🕒 Lógica de generación

Dada una hora en formato `HH:mm:ss`, se define:
- `X = minutos`
- `Y = segundos`
- Números iniciales: `X` y `Y`
- Se generan **Y elementos** a partir de esas semillas (no se cuentan las semillas)
- La secuencia final es ordenada de forma **descendente**

📌 Ejemplo:

Hora: `13:21:08`

- Semillas: `2,1`, `8`
- Cantidad de elementos: `8`
- Serie generada: `21, 8, 29, 37, 66, 103, 169, 272, 441, 713`
- Serie descendente: `713, 441, 272, 169, 103, 66, 37, 29, 8, 21`

---

## 🔒 Seguridad

La API expone un endpoint para consultar las series generadas que requiere autenticación básica (`Basic Auth`).

### Autenticación básica
spring.security.user.name=
spring.security.user.password=

📦 Endpoints
🔹 POST https://fibonacci-api-y490.onrender.com/api/fibonacci/hora
📩 Genera una secuencia de Fibonacci a partir de una hora actual.

 GET /api/fibonacci/all
📋 Retorna todas las secuencias generadas y almacenadas en la base de datos.

Requiere autenticación básica.

📤 Respuesta (ejemplo):


[
  {
    "horaProcesamiento": "2025-05-05T13:21:08",
    "semillas": "21,8",
    "cantidad": 8,
    "serie": "713,441,272,169,103,66,37,29,8,21",
    "destinatarios": ["correo1@dominio.com", "correo2@dominio.com"]
  }
]

## 🧱 Arquitectura del Proyecto
┌────────────────────────────┐
│    Spring Boot Application │
│                            │
│ ┌────────────────────────┐ │
│ │   FibonacciController  │◄───── Recepción de hora y retorno de resultados
│ └────────────────────────┘ │
│ ┌────────────────────────┐ │
│ │   FibonacciService     │◄───── Lógica principal de generación
│ └────────────────────────┘ │
│ ┌────────────────────────┐ │
│ │   EmailService         │◄───── Envío de resultados por correo
│ └────────────────────────┘ │
│ ┌────────────────────────┐ │
│ │   FibonacciResult      │◄───── Entidad JPA (modelo)
│ └────────────────────────┘ │
│ ┌────────────────────────┐ │
│ │   FibonacciRepository  │◄───── Acceso a base de datos (PostgreSQL)
│ └────────────────────────┘ │
└────────────────────────────┘

## 📂 Estructura del Proyect
src/
├── controller/
│   └── FibonacciController.java
├── model/
│   └── FibonacciResult.java
├── repository/
│   └── FibonacciRepository.java
├── service/
│   ├── FibonacciService.java
│   └── EmailService.java
├── dto/
│   └── HoraRequest.java
└── test/
├── FibonacciServiceTest.java
└── FibonacciControllerTest.java

## 🧪 Pruebas
Para ejecutar las pruebas:

mvn test

✔ Pruebas unitarias para la generación descendente de Fibonacci.

✔ Pruebas de integración para los endpoints principales.

## 🚀 Despliegue

La API está desplegada en Render.

https://fibonacci-api-y490.onrender.com/resultados.html

## 👨‍💻 Autor
Desarrollado por Sebastián Díaz
📧 Contacto: sebasdj2006@gmail.com
🔗 GitHub: @SebastianDiazJ