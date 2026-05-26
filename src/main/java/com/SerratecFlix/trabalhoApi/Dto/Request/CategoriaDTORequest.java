package com.SerratecFlix.trabalhoApi.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de dados para inserção de uma categoria de filme/série.")
public class CategoriaDTORequest {

    @NotBlank(message = "O nome deve ser preenchido")
    @Schema(description = "Nome da categoria.", example = "Comédia")
    private String nome;

    @NotBlank(message = "A descrição deve ser preenchida")
    @Schema(description = "Descrição da categoria", example = "Histórias leves e bem-humoradas feitas para fazer rir")
    private String descricao;

    public CategoriaDTORequest() {
    }

    public CategoriaDTORequest(@NotBlank(message = "O nome deve ser preenchido") String nome,
            @NotBlank(message = "A descrição deve ser preenchida") String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
}
