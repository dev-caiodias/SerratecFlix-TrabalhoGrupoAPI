package com.SerratecFlix.trabalhoApi.Dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para criação ou atualização de usuário")
public class UsuarioDTORequest {
    @NotBlank
    @Schema(description = "Nome completo", example = "Caio Vinícius Dias")
    private String nome;
    @NotBlank
    @Email
    @Schema(description = "Email válido", example = "caio@email.com")
    private String email;
    @NotBlank
    @Size(max = 20)
    @Schema(description = "Username válido", example = "Caio2005")
    private String userName;
    @NotBlank
    @Size(min = 8)
    @Schema(description = "Senha do usuário", example = "12345")
    private String senha;
}
