package com.SerratecFlix.trabalhoApi.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaFavoritosDTORequest {

    @NotBlank
    private String favoritos;

    @NotNull
    private Boolean privada;

    @NotNull
    private Long usuarioId;
}
