package com.ejemplo.fibo.fibonacci_api.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    // Expresión regular para validar correos electrónicos simples
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    public void enviarEmail(String destino, String asunto, String cuerpoTexto) throws MessagingException {
        if (!esCorreoValido(destino)) {
            throw new IllegalArgumentException("Correo inválido: " + destino);
        }

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, false, "UTF-8");

        helper.setTo(destino);
        helper.setSubject(asunto);
        helper.setText(cuerpoTexto, false);

        javaMailSender.send(mensaje);
    }

    private boolean esCorreoValido(String correo) {
        return EMAIL_PATTERN.matcher(correo).matches();
    }
}