package com.ejemplo.fibo.fibonacci_api.service;

import com.ejemplo.fibo.fibonacci_api.model.FibonacciResult;
import com.ejemplo.fibo.fibonacci_api.repository.FibonacciResultRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FibonacciService {

    @Autowired
    private FibonacciResultRepository repository;

    @Autowired
    private EmailService emailService;

    public List<Integer> generarSerieDescendente(int a, int b, int cantidad) {
        List<Integer> serie = new ArrayList<>();
        serie.add(a);
        serie.add(b);

        for (int i = 2; i < cantidad + 2; i++) { // +2 porque semilla1 y semilla2 ya se agregaron
            int next = serie.get(i - 1) + serie.get(i - 2);
            serie.add(next);
        }

        Collections.reverse(serie); // serie descendente
        return serie;
    }


    public FibonacciResult guardarResultado(String horaStr, List<Integer> serie) {
        FibonacciResult result = new FibonacciResult();
        result.setA((long) serie.get(serie.size() - 2)); // semilla1 original
        result.setB((long) serie.get(serie.size() - 1)); // semilla2 original
        result.setCantidad(serie.size() - 2); // solo los números extra
        result.setResultado(serie.toString());
        result.setFechaEnvio(LocalDateTime.now());
        result.setEmail("Sistema - desde hora: " + horaStr);
        return repository.save(result);
    }
}

