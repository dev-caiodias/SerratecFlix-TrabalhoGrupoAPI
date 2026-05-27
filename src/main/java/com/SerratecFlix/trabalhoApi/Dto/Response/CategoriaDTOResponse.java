package com.SerratecFlix.trabalhoApi.Dto.Response;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de dados para apresentação de informações de categoria ao usuario.")
public class CategoriaDTOResponse {

    @Schema(description = "Chave primária de identificação da categoria.", example = "12")
    private Long id;

    @Schema(description = "Nome da categoria.", example = "Comédia")
    private String nome;

    @Schema(description = "Descrição da categoria", example = "Histórias leves e bem-humoradas feitas para fazer rir")
    private String descricao;

    public CategoriaDTOResponse() {
    }

    public CategoriaDTOResponse(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
