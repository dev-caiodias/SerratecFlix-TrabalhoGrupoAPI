package com.SerratecFlix.trabalhoApi.Dto.Request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(description = "Modelo de dados para registro de uma avaliação de filme.")
public class AvaliacaoFilmeDTOResquest {

    @NotNull(message = "O ID do usuario deve ser inserido")
    @Schema(description = "FK do usuário que realizou a avaliação do filme.", example = "27")
    private Long usuarioId;

    @NotNull(message = "O ID do filme deve ser inserido")
    @Schema(description = "FK do filme que foi avaliado.", example = "52")
    private Long filmeId;

    @NotNull(message = "A nota do filme deve ser inserida.")
    @DecimalMin("0.0") @DecimalMax("10.0")
    @Schema(description = "Nota de avaliação do filme", example = "7.9")
    private double nota;

    @NotBlank(message = "Este campo deve ser preenchido")
    @Size(max = 100)
    @Schema(description = "Comentário que irá acompanhar a nota.", example = "Muito bom!")
    private String comentario;

}
