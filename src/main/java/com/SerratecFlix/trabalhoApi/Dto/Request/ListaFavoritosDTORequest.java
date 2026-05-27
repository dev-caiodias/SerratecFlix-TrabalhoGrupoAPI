package com.SerratecFlix.trabalhoApi.Dto.Request;

import com.SerratecFlix.trabalhoApi.Domain.Filme;
import com.SerratecFlix.trabalhoApi.Domain.ListaFavoritos;
import com.SerratecFlix.trabalhoApi.Domain.Serie;
import com.SerratecFlix.trabalhoApi.Dto.Response.FilmeResponseDTO;
import com.SerratecFlix.trabalhoApi.Dto.Response.SerieResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<Serie> serie;
    private List<Filme> filme;
}
