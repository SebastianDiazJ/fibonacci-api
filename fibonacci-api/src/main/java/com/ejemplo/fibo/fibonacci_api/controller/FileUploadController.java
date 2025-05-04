package com.ejemplo.fibo.fibonacci_api.controller;

import com.ejemplo.fibo.fibonacci_api.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    @Autowired
    private FibonacciService fibonacciService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Leer contenido del archivo
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            String[] parts = content.split(",");

            long a = Long.parseLong(parts[0].trim());
            long b = Long.parseLong(parts[1].trim());
            int cantidad = Integer.parseInt(parts[2].trim());
            String email = parts[3].trim();

            // Llamar al servicio correctamente
            fibonacciService.procesarYGuardarYEnviar(a, b, cantidad, email);

            return ResponseEntity.ok("Archivo procesado correctamente");
        } catch (Exception e) {
            e.printStackTrace(); // para ayudarte a ver el error en consola
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar el archivo.");
        }
    }
}
