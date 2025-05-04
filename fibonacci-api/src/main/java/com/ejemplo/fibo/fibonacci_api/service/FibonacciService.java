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

@Service
public class FibonacciService {

    @Autowired
    private FibonacciResultRepository repository;

    @Autowired
    private EmailService emailService;

    public void procesarYGuardarYEnviar(long a, long b, int cantidad, String email) throws MessagingException {
        List<Long> secuencia = new ArrayList<>();
        secuencia.add(a);
        secuencia.add(b);

        for (int i = 2; i < cantidad; i++) {
            secuencia.add(secuencia.get(i - 1) + secuencia.get(i - 2));
        }

        String resultado = secuencia.toString();
        LocalDateTime ahora = LocalDateTime.now();

        FibonacciResult result = new FibonacciResult();
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
