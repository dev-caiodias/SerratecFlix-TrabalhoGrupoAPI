package com.SerratecFlix.trabalhoApi.Dto.Response;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de dados para apresentação de informações de avaliação de um filme ao usuario.")
public class AvaliacaoFilmeDTOResponse {

    @Schema(description = "Chave primária de identificação da avaliação cinematográfica.", example = "15")
    private Long id;

    @Schema(description = "Nota de avaliação do filme.", example = "7.9")
    private Double nota;

    @Schema(description = "Comentario de avaliação que acompanha a nota.", example = "Muito Legal!")
    private String comentario;

    @Schema(description = "Data em que a avaliação foi registrada", example = "2026-05-27")
    private LocalDateTime dataAvaliacao;

    @Schema(description = "O nome do autor da avaliação", example = "João Carlos")
    private String nomeUsuario;

    @Schema(description = "Título do filme avaliado", example = "De volta pro futuro")
    private  String tituloFilme;

    public AvaliacaoFilmeDTOResponse() {
    }

    public AvaliacaoFilmeDTOResponse(Long id, Double nota, String comentario, LocalDateTime dataAvaliacao,
            String nomeUsuario, String tituloFilme) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
        this.nomeUsuario = nomeUsuario;
        this.tituloFilme = tituloFilme;
    }

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

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    
}
