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

    /**
     * Genera una secuencia Fibonacci desde dos números iniciales (a, b), guarda el resultado
     * en base de datos, y lo envía por correo electrónico al destinatario indicado.
     */
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
        emailService.enviarEmail(email, resultado, String.valueOf(ahora));
    }

    // Generar una serie de Fibonacci con N elementos a partir de dos semillas
    public List<Integer> generarSerieDesdeHora(int a, int b) {
        List<Integer> serie = new ArrayList<>();
        serie.add(a);
        serie.add(b);

        int cantidad = b; // N = segundos

        for (int i = 2; i < cantidad; i++) {
            serie.add(serie.get(i - 1) + serie.get(i - 2));
        }
        return serie;

    }

    // Guardar resultado y retornar el objeto guardado
    public FibonacciResult guardarResultado(String horaStr, List<Integer> serie) {
        FibonacciResult result = new FibonacciResult();

        result.setA((long) serie.get(0));
        result.setB((long) serie.get(1));
        result.setCantidad(serie.size());
        result.setResultado(serie.toString());
        result.setFechaEnvio(LocalDateTime.now());
        result.setEmail("Sistema - desde hora: " + horaStr);

        return repository.save(result);
    }

}
