package com.ejemplo.fibo.fibonacci_api.controller;
import com.ejemplo.fibo.fibonacci_api.model.FibonacciResult;
import com.ejemplo.fibo.fibonacci_api.repository.FibonacciResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ViewHtml {
    @Autowired
    private FibonacciResultRepository repository;

    @GetMapping("/resultados-json")
    public List<FibonacciResult> resultadosJson() {
        return repository.findAll();
    }
}






