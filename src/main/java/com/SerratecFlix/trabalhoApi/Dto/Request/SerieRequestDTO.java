package com.SerratecFlix.trabalhoApi.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class SerieRequestDTO {

    @NotBlank(message = "O título da série é obrigatório.")
    @Size(max = 100, message = "O título não pode ter mais de 100 caracteres.")
    private String titulo;

    @NotBlank(message = "A descrição da série é obrigatória.")
    private String descricao;

    @NotNull(message = "O número de temporadas é obrigatório.")
    @PositiveOrZero(message = "O número de temporadas não pode ser negativo.")
    private Integer temporadas;

    @NotNull(message = "O número total de episódios é obrigatório.")
    @PositiveOrZero(message = "O número de episódios não pode ser negativo.")
    private Integer episodios;

    private LocalDate dataLancamento;

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
}