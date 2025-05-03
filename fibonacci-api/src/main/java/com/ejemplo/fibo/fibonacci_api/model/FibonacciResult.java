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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInputValues() {
        return inputValues;
    }

    public void setInputValues(String inputValues) {
        this.inputValues = inputValues;
    }

    public String getGeneratedSequence() {
        return generatedSequence;
    }

    public void setGeneratedSequence(String generatedSequence) {
        this.generatedSequence = generatedSequence;
    }

    public String getEmailSentTo() {
        return emailSentTo;
    }

    public void setEmailSentTo(String emailSentTo) {
        this.emailSentTo = emailSentTo;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
