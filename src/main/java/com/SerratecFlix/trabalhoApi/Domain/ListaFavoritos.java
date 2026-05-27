package com.SerratecFlix.trabalhoApi.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lista-favoritos")
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

    @Column(nullable = false)
    private boolean privada;

    @Column(name = "dataCriacao", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuario usuario;


    @ManyToMany
    @JoinTable(name = "lista_filme", joinColumns = @JoinColumn(name = "lista_id"),
    inverseJoinColumns = @JoinColumn(name = "filme_id"))
    private List<Filme> filme = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "lista_serie", joinColumns = @JoinColumn(name = "lista_id"),
    inverseJoinColumns = @JoinColumn(name = "serie_id"))
    private List<Serie> serie = new ArrayList<>();


    public void adicionaFilme(Filme filme){
        this.filme.add(filme);
    }

    public void adicionaSerie(Serie serie){
        this.serie.add(serie);
    }



}
