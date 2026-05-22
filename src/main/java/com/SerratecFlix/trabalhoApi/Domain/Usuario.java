package com.SerratecFlix.trabalhoApi.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String nome;
    @Column
    @Email
    private String email;
    @Column
    @Size()
    private String userName;
    private String senha;
    private LocalDate dataCriacao;
}
