package com.SerratecFlix.trabalhoApi.Dto.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(description = "Modelo de dados para apresentação de informações de categoria ao usuario.")
public class CategoriaDTOResponse {

    @Schema(description = "Chave primária de identificação da categoria.", example = "12")
    private Long id;

    @Schema(description = "Nome da categoria.", example = "Comédia")
    private String nome;

    @Schema(description = "Descrição da categoria", example = "Histórias leves e bem-humoradas feitas para fazer rir")
    private String descricao;

}
