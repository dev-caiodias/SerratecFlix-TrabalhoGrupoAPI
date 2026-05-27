package com.SerratecFlix.trabalhoApi.Domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer temporadas;

    @Column(nullable = false)
    private Integer episodios;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "nota_media")
    private Double notaMedia = 0.0;

    @ManyToMany
    @JoinTable(name = "serie_categoria", joinColumns = @JoinColumn(name = "serie_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AvaliacaoSerie> avaliacoes;
    
    @ManyToMany
    private List<Categoria> categorias; 

<<<<<<< HEAD
    public Serie() {
    }

    public Serie(Long id, String titulo, String descricao, Integer temporadas, Integer episodios,
            LocalDate dataLancamento, Double notaMedia) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.temporadas = temporadas;
        this.episodios = episodios;
        this.dataLancamento = dataLancamento;
        this.notaMedia = notaMedia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(Integer temporadas) {
        this.temporadas = temporadas;
    }

    public Integer getEpisodios() {
        return episodios;
    }

    public void setEpisodios(Integer episodios) {
        this.episodios = episodios;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public List<AvaliacaoSerie> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoSerie> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
=======
    @ManyToMany(mappedBy = "series")
    @JsonIgnore
    private List<ListaFavoritos> listasFavoritos;
>>>>>>> c36d67ed2cc94d266fa452d969a8187058c3785b
}