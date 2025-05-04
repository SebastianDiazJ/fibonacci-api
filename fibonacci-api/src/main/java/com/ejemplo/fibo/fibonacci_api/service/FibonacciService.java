package com.ejemplo.fibo.fibonacci_api.service;

import com.ejemplo.fibo.fibonacci_api.model.FibonacciResult;
import com.ejemplo.fibo.fibonacci_api.repository.FibonacciResultRepository;
import com.ejemplo.fibo.fibonacci_api.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//esta clase se encarga del servicio de Fibonacci para calcular la secuencia Fibonacci
@Service
public class FibonacciService {

    @Autowired
    private FibonacciResultRepository repository;//servicio para acceder a los resultados de Fibonacci

    @Autowired
    private EmailService emailService;// Servicio para enviar correos electrónicos
    // Método para procesar la secuencia Fibonacci, guardar el resultado en la base de datos y enviar un correo electrónico
    public void procesarYGuardarYEnviar(long a, long b, int cantidad, String email) throws MessagingException {// se usa para procesar la secuencia Fibonacci
        List<Long> secuencia = new ArrayList<>();// Lista para almacenar la secuencia Fibonacci
        secuencia.add(a);
        secuencia.add(b);

        for (int i = 2; i < cantidad; i++) {//se usa para calcular la secuencia Fibonacci segun la cantidad solicitada
            secuencia.add(secuencia.get(i - 1) + secuencia.get(i - 2));//se restan los dos ultimos elementos de la lista porque son los dos ultimos elementos de la secuencia Fibonacci
        }

        String resultado = secuencia.toString();// Convertir la lista a una cadena para almacenar en la base de datos
        LocalDateTime ahora = LocalDateTime.now();

        FibonacciResult result = new FibonacciResult();//se crea un nuevo objeto FibonacciResult porque se va a guardar en la base de datos
        result.setA(a);
        result.setB(b);
        result.setCantidad(cantidad);
        result.setResultado(resultado);
        result.setFechaEnvio(ahora);
        result.setEmail(email);

        repository.save(result);
        emailService.enviarResultado(email, resultado, ahora);
    }
}
