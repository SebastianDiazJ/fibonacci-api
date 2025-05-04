package com.ejemplo.fibo.fibonacci_api.dto;
//esta clase se encarga de recibir la solicitud de Fibonacci
public class FibonacciRequest {// Clase DTO para recibir la solicitud de Fibonacci
    private long a;
    private long b;
    private int cantidad;
    private String email;

    // Getters y Setters
    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getB() {
        return b;
    }

    public void setB(long b) {
        this.b = b;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
