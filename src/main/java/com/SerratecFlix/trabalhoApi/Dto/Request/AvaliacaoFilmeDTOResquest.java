package com.SerratecFlix.trabalhoApi.Dto.Request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AvaliacaoFilmeDTOResquest {

    @NotNull(message = "O ID do usuario deve ser inserido")
    private Long usuarioId;

    @NotNull(message = "O ID do filme deve ser inserido")
    private Long filmeId;

    @NotNull(message = "A nota do filme deve ser inserida.")
    @DecimalMin("0.0") @DecimalMax("10.0")
    private double nota;

    @NotBlank(message = "Este campo deve ser preenchido")
    @Size(max = 100)
    private String comentario;

    public AvaliacaoFilmeDTOResquest() {
    }

    public AvaliacaoFilmeDTOResquest(@NotNull(message = "O ID do usuario deve ser inserido") Long usuarioId,
            @NotNull(message = "O ID do filme deve ser inserido") Long filmeId,
            @NotNull(message = "A nota do filme deve ser inserida.") @DecimalMin("0.0") @DecimalMax("10.0") double nota,
            @NotBlank(message = "Este campo deve ser preenchido") @Size(max = 100) String comentario) {
        this.usuarioId = usuarioId;
        this.filmeId = filmeId;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(Long filmeId) {
        this.filmeId = filmeId;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    
}
