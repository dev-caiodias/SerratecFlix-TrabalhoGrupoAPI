package com.SerratecFlix.trabalhoApi.Dto.Response;

import com.SerratecFlix.trabalhoApi.Domain.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Schema(description = "Dados retornados do usuário")
public class UsuarioDTOResponse {
    @Schema(description = "ID do usuário", example = "1")
    private Long id;
    @Schema(description = "Nome do usuário", example = "Caio Vinícius")
    private String nome;
    @Schema(description = "Email do usuario", example = "caio@email.com")
    private String email;
    @Schema(description = "Username do usuario", example = "Caio2005")
    private String userName;
    @Schema(description = "Data da criação do usuario")
    private LocalDateTime dataCriacao;

    public UsuarioDTOResponse (Usuario u){
        this.id = u.getId();
        this.nome = u.getNome();
        this.email = u.getEmail();
        this.userName = u.getUserName();
        this.dataCriacao = u.getDataCriacao();
    }
}
