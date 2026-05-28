package com.SerratecFlix.trabalhoApi.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(description = "Modelo de dados para inserção de uma categoria de filme/série.")
public class CategoriaDTORequest {

    @NotBlank(message = "O nome deve ser preenchido")
    @Schema(description = "Nome da categoria.", example = "Comédia")
    private String nome;

    @NotBlank(message = "A descrição deve ser preenchida")
    @Schema(description = "Descrição da categoria", example = "Histórias leves e bem-humoradas feitas para fazer rir")
    private String descricao;

}
