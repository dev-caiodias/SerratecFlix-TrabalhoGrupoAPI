package com.SerratecFlix.trabalhoApi.Dto.Response;

import com.SerratecFlix.trabalhoApi.Domain.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UsuarioDTOResponse {
    private Long id;
    private String nome;
    private String email;
    private String userName;
    private LocalDateTime dataCriacao;

    public UsuarioDTOResponse (Usuario u){
        this.id = u.getId();
        this.nome = u.getNome();
        this.email = u.getEmail();
        this.userName = u.getUserName();
        this.dataCriacao = u.getDataCriacao();
    }
}
