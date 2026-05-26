package com.SerratecFlix.trabalhoApi.Dto.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


public record ListaFavoritosDTOResponse(


     Long id,
     String favoritos,
     LocalDateTime dataCriacao,
     String nomeUsuario,
     List<FilmeResponse> filme,
     List<SerieResponseDTO> serie
){}



