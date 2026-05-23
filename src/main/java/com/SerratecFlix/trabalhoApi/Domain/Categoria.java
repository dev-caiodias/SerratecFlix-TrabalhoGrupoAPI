package com.SerratecFlix.trabalhoApi.Domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Categoria")
    private Long id;

    @NotBlank(message = "Este campo deve ser preenchido.") 
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 100)
    @Column(name = "descricao", length = 100)
    private String descricao;

    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Filme> filmes;

    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Serie> series;


    
    public Categoria() {
    }



    public Categoria(Long id, @NotBlank(message = "Este campo deve ser preenchido.") @Size(max = 50) String nome,
            @Size(max = 100) String descricao, List<Filme> filmes, List<Serie> series) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.filmes = filmes;
        this.series = series;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getDescricao() {
        return descricao;
    }



    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
