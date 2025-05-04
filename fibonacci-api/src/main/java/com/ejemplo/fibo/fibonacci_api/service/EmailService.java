package com.ejemplo.fibo.fibonacci_api.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarResultado(String destino, String resultado, LocalDateTime fecha) throws MessagingException {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

        helper.setTo(destino);
        helper.setSubject("Resultado de la Secuencia Fibonacci");
        helper.setText("Hola,\n\nAquí tienes el resultado:\n\n" + resultado +
                "\n\nFecha de envío: " + fecha + "\n\nSaludos,\nTu API");

        javaMailSender.send(mensaje);
    }
}
