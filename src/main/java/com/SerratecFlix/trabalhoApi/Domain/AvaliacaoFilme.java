package com.SerratecFlix.trabalhoApi.Domain;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class AvaliacaoFilme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;
}

//Classe temporaria pra testes
=======
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AvaliacaoFilme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao_filme")
    private Long id;

    @NotNull(message = "A nota do filme deve ser inserida.")
    @DecimalMin("0.0") @DecimalMax("10.0")
    @Column(name = "nota_filme", nullable = false)
    private double nota;

    @NotBlank(message = "Este campo deve ser preenchido")
    @Size(max = 100)
    @Column(name = "cometario", length = 100)
    private String comentario;

    @FutureOrPresent
    @NotNull(message = "Este campo deve ser preenchido")
    @Column(name = "data_avaliacao", nullable = false)
    private LocalDateTime dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    @JsonBackReference
    private Filme filme;

    public AvaliacaoFilme() {
    }

    public AvaliacaoFilme(Long id,
            @NotNull(message = "A nota do filme deve ser inserida.") @Positive(message = "A nota do filme deve ser maior que 0.1") double nota,
            @Size(max = 100) String comentario,
            @FutureOrPresent @NotNull(message = "Este campo deve ser preenchido") LocalDateTime dataAvaliação,
            Usuario usuario, Filme filme) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliação;
        this.usuario = usuario;
        this.filme = filme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDataAvaliação() {
        return dataAvaliacao;
    }

    public void setDataAvaliação(LocalDateTime dataAvaliação) {
        this.dataAvaliacao = dataAvaliação;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    

}
>>>>>>> origin/main
