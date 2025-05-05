package com.ejemplo.fibo.fibonacci_api.controller;

import com.ejemplo.fibo.fibonacci_api.model.FibonacciResult;
import com.ejemplo.fibo.fibonacci_api.service.EmailService;
import com.ejemplo.fibo.fibonacci_api.service.FibonacciService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/fibonacci")
public class FibonacciHoraController {

    @Autowired
    private FibonacciService fibonacciService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/hora")
    public ResponseEntity<?> generarDesdeHoraActual() {
        try {
            // Obtener hora actual del sistema
            LocalTime hora = LocalTime.now();

            int minutos = hora.getMinute(); // ejemplo: 49
            int segundos = hora.getSecond(); // ejemplo: 08

            // Semillas: decena y unidad del minuto actual
            int semilla1 = minutos / 10;  // decena de los minutos
            int semilla2 = minutos % 10;  // unidad de los minutos

            // Validación
            if (segundos <= 0) {
                return ResponseEntity.badRequest().body("Los segundos no pueden ser cero.");
            }

            // Generar serie completa (semillas + cantidad basada en segundos), y en orden descendente
            List<Integer> serie = fibonacciService.generarSerieDescendente(semilla1, semilla2, segundos);

            // Guardar resultado en DB
            FibonacciResult resultado = fibonacciService.guardarResultado(hora.toString(), serie);

            // Construir mensaje
            String mensaje = "Hola, esta es la serie generada a las " + hora +
                    "\nSemillas: " + semilla1 + ", " + semilla2 +
                    "\nSerie: " + serie +
                    "\n\nGracias por usar nuestra API.";

            // Enviar a ambos correos
            emailService.enviarEmail("sdiazj@proteccion.com.co", "Prueba Técnica Sebastián Diaz", mensaje);
            emailService.enviarEmail("sebasdj2006@gmail.com", "Prueba Técnica Sebastián Diaz", mensaje);

            return ResponseEntity.ok(serie);

        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al enviar correo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error procesando la hora automática: " + e.getMessage());
        }
    }

}

