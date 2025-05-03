package com.ejemplo.fibo.fibonacci_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FibonacciResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputValues;
    private String generatedSequence;
    private String emailSentTo;
    private LocalDateTime sentAt;

    // Getters y Setters
}

