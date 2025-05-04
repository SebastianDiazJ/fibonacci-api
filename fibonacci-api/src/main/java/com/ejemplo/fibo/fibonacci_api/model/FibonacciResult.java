package com.ejemplo.fibo.fibonacci_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
//esta clase se encarga de guardar los resultados de Fibonacci
@Entity
public class FibonacciResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cambiados de primitivos a objetos para permitir null si es necesario
    private Long a;
    private Long b;
    private Integer cantidad;

    private String resultado;
    private String email;

    private LocalDateTime fechaEnvio;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public Long getA() {
        return a;
    }

    public void setA(Long a) {
        this.a = a;
    }

    public Long getB() {
        return b;
    }

    public void setB(Long b) {
        this.b = b;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
