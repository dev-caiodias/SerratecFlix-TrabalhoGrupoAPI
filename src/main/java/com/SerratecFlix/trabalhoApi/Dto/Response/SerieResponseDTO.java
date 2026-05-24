package com.SerratecFlix.trabalhoApi.Dto.Response;

import com.SerratecFlix.trabalhoApi.Domain.Serie;
import java.time.LocalDate;

public class SerieResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Integer temporadas;
    private Integer episodios;
    private LocalDate dataLancamento;
    private Double notaMedia;

    public SerieResponseDTO() {
    }

    public SerieResponseDTO(Serie serie) {
        this.id = serie.getId();
        this.titulo = serie.getTitulo();
        this.descricao = serie.getDescricao();
        this.temporadas = serie.getTemporadas();
        this.episodios = serie.getEpisodios();
        this.dataLancamento = serie.getDataLancamento();
        this.notaMedia = serie.getNotaMedia();
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
}