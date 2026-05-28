package com.SerratecFlix.trabalhoApi.Domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
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

}
