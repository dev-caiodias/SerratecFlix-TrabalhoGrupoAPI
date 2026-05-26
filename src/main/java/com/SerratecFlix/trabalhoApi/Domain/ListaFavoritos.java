package com.SerratecFlix.trabalhoApi.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListaFavoritos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Column(name = "", nullable = false)
    private String favoritos;


    private boolean privada;


    @Column
    private LocalDate dataCriacao;


    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuario usuario;


    @ManyToMany
    @JoinTable(name = "lista_filme", joinColumns = @JoinColumn(name = "lista_id"),
    inverseJoinColumns = @JoinColumn(name = "filme_id"))
    private Filme filme;


    @ManyToMany
    @JoinTable(name = "lista_serie", joinColumns = @JoinColumn(name = "lista_id"),
    inverseJoinColumns = @JoinColumn(name = "serie_id"))
    private Serie serie;


}
