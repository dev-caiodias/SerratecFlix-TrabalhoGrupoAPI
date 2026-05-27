package com.SerratecFlix.trabalhoApi.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public void enviarBoasVindas(String destinatario, String nomeUsuario){
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom(remetente);
        mensagem.setTo(destinatario);
        mensagem.setSubject("Bem vindo ao SerratecFLix!");
        mensagem.setText("Olá, " + nomeUsuario + "!\n\n" +
                "Sua conta foi criada no SerratecFlix. \n" +
                "Explore o nosso catálogo e crie suas listas favoritas! \n\n" +
                "Equipe SerratecFlix"
        );

        mailSender.send(mensagem);
    }
}
