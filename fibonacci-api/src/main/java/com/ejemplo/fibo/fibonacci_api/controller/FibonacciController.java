package com.ejemplo.fibo.fibonacci_api.controller;

import com.ejemplo.fibo.fibonacci_api.dto.FibonacciRequest;
import com.ejemplo.fibo.fibonacci_api.model.FibonacciResult;
import com.ejemplo.fibo.fibonacci_api.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fibonacci")// Endpoint base
public class FibonacciController {

    @Autowired
    private FibonacciService service;// Inyectar el servicio

    @PostMapping
    public FibonacciResult generateAndSave(@RequestBody FibonacciRequest request) {// Recibir la solicitud
        List<Long> sequence = service.generateFibonacci(
                request.getA(),
                request.getB(),
                request.getCantidad()
        );

        String sequenceStr = sequence.toString();

        return service.saveResult(request.getA() + ", " + request.getB(), sequenceStr, request.getEmail());
    }

    @GetMapping("/ping")
    public String ping() {
        return "Fibonacci API funcionando";
    }
}
