package com.SerratecFlix.trabalhoApi.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "usuario")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"avaliacoesFilme", "avaliacoesSerie", "listaFavoritos"})

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

    @Column(unique = true)
    @Size(max = 20)
    private String userName;

    @NotBlank
    @Size(min = 8)
    private String senha;

    @Column
    @CreationTimestamp
    private LocalDateTime dataCriacao;
}
