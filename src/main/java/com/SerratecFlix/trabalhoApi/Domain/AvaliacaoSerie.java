package com.SerratecFlix.trabalhoApi.Domain;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Classe que representa a avaliação de uma série.")
public class AvaliacaoSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da avaliação.", example = "1")
    private Long id;

    @Min(value = 0, message = "A nota mínima é 0.")
    @Max(value = 10, message = "A nota máxima é 10.")
    @Schema(description = "Nota da avaliação, deve estar entre 0 e 10.", example = "8.5")
    private Double nota;

    @NotBlank(message = "O comentário não pode estar em branco.")
    @Schema(description = "Comentário da avaliação.", example = "Ótima série!")
    private String comentario;

    @Schema(description = "Data em que a avaliação foi realizada.", example = "2023-06-15")
    private LocalDate dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @Schema(description = "Usuário que fez a avaliação.")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    @Schema(description = "Série que foi avaliada.")
    private Serie serie;

    public AvaliacaoSerie() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }
}
