package com.ejemplo.fibo.fibonacci_api.controller;

import com.ejemplo.fibo.fibonacci_api.model.FibonacciResult;
import com.ejemplo.fibo.fibonacci_api.repository.FibonacciResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller// Clase controladora para manejar las vistas de la aplicación
public class FibonacciViewController {// Controlador para manejar las vistas de la aplicación

    @Autowired
    private FibonacciResultRepository repository;

    @GetMapping("/resultados")// Mapeo de la ruta /resultados
    public String verResultados(Model model) {
        List<FibonacciResult> resultados = repository.findAll();
        model.addAttribute("resultados", resultados);
        return "resultados"; // esto va a usar src/main/resources/templates/resultados.html
    }
}
