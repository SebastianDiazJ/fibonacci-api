package com.ejemplo.fibo.fibonacci_api.controller;

import com.ejemplo.fibo.fibonacci_api.dto.HoraRequest;
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
    public ResponseEntity<?> generarDesdeHora(@RequestBody HoraRequest request) {
        try {
            String horaStr = request.getHora(); // formato esperado HH:mm:ss
            LocalTime hora = LocalTime.parse(horaStr);

            int semilla1 = hora.getMinute() % 10;
            int semilla2 = hora.getSecond() % 10;

            if (semilla2 <= 0) return ResponseEntity.badRequest().body("Los segundos no pueden ser cero.");

            List<Integer> serie = fibonacciService.generarSerieDesdeHora(semilla1, semilla2);
            FibonacciResult resultado = fibonacciService.guardarResultado(horaStr, serie);

            String mensaje = "Hola, esta es la serie generada a las " + horaStr +
                    "\nSemillas: " + semilla1 + ", " + semilla2 +
                    "\nSerie: " + serie +
                    "\n\nGracias por usar nuestra API.";

            emailService.enviarEmail("sdiazj@proteccion.com.co", "Prueba Técnica Sebastián Diaz", mensaje);
            emailService.enviarEmail("sebasdj2006@gmail.com", "Prueba Técnica Sebastián Diaz", mensaje);

            return ResponseEntity.ok(serie);

        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al enviar correo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error procesando la hora: " + e.getMessage());
        }
    }
}
