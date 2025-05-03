package com.ejemplo.fibo.fibonacci_api.service;
import com.ejemplo.fibo.fibonacci_api.model.FibonacciResult;
import com.ejemplo.fibo.fibonacci_api.repository.FibonacciResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//creacion de la clase FibonacciService
@Service
public class FibonacciService {

    @Autowired
    private FibonacciResultRepository repository;// Inyección de dependencia del repositorio

    public List<Long> generateFibonacci(long a, long b, int count) {
        List<Long> sequence = new ArrayList<>();// Lista para almacenar la secuencia de Fibonacci
        sequence.add(a);
        sequence.add(b);

        for (int i = 2; i < count; i++) {// Generación de la secuencia
            long next = sequence.get(i - 1) + sequence.get(i - 2);// Suma de los dos últimos números
            sequence.add(next);// Agregar el siguiente número a la secuencia
        }

        return sequence;// Devolver la secuencia generada
    }

    public FibonacciResult saveResult(String input, String sequence, String email) {// Guardar el resultado en la base de datos
        FibonacciResult result = new FibonacciResult();
        result.setInputValues(input);// Establecer los valores de entrada
        result.setGeneratedSequence(sequence);// Establecer la secuencia generada
        result.setEmailSentTo(email);// Establecer el correo electrónico al que se envió
        result.setSentAt(LocalDateTime.now());// Establecer la fecha y hora de envío

        return repository.save(result);// Guardar el resultado en la base de datos
    }
}