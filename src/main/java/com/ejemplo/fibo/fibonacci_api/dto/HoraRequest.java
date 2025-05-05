package com.ejemplo.fibo.fibonacci_api.dto;
//esta clase se encarga de recibir la solicitud de Fibonacci
public class HoraRequest {// Clase DTO para recibir la solicitud de Fibonacci
    private String hora; // formato "HH:MM:SS"

    public HoraRequest() {}// Constructor vacío

    public HoraRequest(String hora) {//
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
