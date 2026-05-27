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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Classe que representa a avaliação de uma série.")
public class AvaliacaoSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da avaliação.", example = "1")
    private Long id;
    
    @NotNull(message = "A nota é obrigatória.")
    @Min(value = 0, message = "A nota mínima é 0.")
    @Max(value = 10, message = "A nota máxima é 10.")
    @Schema(description = "Nota da avaliação, deve estar entre 0 e 10.", example = "8.5")
    private Double nota;
    
    @Size(max = 500, message = "O comentário deve ter no máximo 500 caracteres.")
    @NotBlank(message = "O comentário não pode estar em branco.")
    @Schema(description = "Comentário da avaliação.", example = "Ótima série!")
    private String comentario;

    @Schema(description = "Data em que a avaliação foi realizada.", example = "2023-06-15")
    @NotNull(message = "A data da avaliação é obrigatória.")
    private LocalDate dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @Schema(description = "Usuário que fez a avaliação.")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    @Schema(description = "Série que foi avaliada.")
    private Serie serie;
}
