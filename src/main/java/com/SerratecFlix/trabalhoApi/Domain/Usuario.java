package com.SerratecFlix.trabalhoApi.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Size(max = 20)
    private String userName;
    @Column
    @NotBlank
    @Size(min = 8)
    private String senha;
    @Column
    private LocalDate dataCriacao;

}
