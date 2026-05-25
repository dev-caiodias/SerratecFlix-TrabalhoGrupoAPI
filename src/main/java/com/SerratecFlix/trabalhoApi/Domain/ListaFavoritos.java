package com.SerratecFlix.trabalhoApi.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

@Entity
public class ListaFavoritos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nomeLista;

    private boolean privada;

    @Column
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "lista_filme",
        joinColumns = @JoinColumn(name = "lista_id"),
        inverseJoinColumns = @JoinColumn(name = "filme_id")
    )
    private List<Filme> filmes;

    @ManyToMany
    @JoinTable(
        name = "lista_serie",
        joinColumns = @JoinColumn(name = "lista_id"),
        inverseJoinColumns = @JoinColumn(name = "serie_id")
    )
    private List<Serie> series;

    public ListaFavoritos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public boolean isPrivada() {
        return privada;
    }

    public void setPrivada(boolean privada) {
        this.privada = privada;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }
}