package com.SerratecFlix.trabalhoApi.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class ListaFavoritos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Column(name = "", nullable = false)
    private String nomeLista;


    private boolean privada;


    @Column
    private LocalDate dataCriacao;


    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference


    @ManyToMany
    @JoinTable(name = "lista_filme", joinColumns = @JoinColumn(name = "lista_id"),
    inverseJoinColumns = @JoinColumn(name = "filme_id"))


    @ManyToMany
    @JoinTable(name = "lista_serie", joinColumns = @JoinColumn(name = "lista_id"),
    inverseJoinColumns = @JoinColumn(name = "serie_id"))

}
