package com.SerratecFlix.trabalhoApi.Dto.Response;

import java.time.LocalDateTime;

public class AvaliacaoFilmeDTOResponse {

    private Long id;

    private Double nota;

    private String comentario;

    private LocalDateTime dataAvaliacao;

    private String nomeUsuario;

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
