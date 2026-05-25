package com.SerratecFlix.trabalhoApi.Dto.Request;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTORequest {

    @NotBlank(message = "O nome deve ser preenchido")
    private String nome;

    @NotBlank(message = "A descrição deve ser preenchida")
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
