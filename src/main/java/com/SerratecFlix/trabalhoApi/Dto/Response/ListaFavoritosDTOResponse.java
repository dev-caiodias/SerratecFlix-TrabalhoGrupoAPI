package com.SerratecFlix.trabalhoApi.Dto.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ListaFavoritosDTOResponse {

    private Long id;
    private String favoritos;
    private LocalDateTime dataCriacao;
    private String nomeUsuario;

    public ListaFavoritosDTOResponse(Long id, String nomeLista, LocalDateTime dataCriacao, String nomeUsuario) {
        this.id = id;
        this.favoritos = nomeLista;
        this.dataCriacao = dataCriacao;
        this.nomeUsuario = nomeUsuario;
    }
}
