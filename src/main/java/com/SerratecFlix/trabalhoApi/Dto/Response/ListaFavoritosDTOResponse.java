package com.SerratecFlix.trabalhoApi.Dto.Response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaFavoritosDTOResponse{



     private Long id;
     private String favoritos;
     private LocalDateTime dataCriacao;
     private String nomeUsuario;
     private List<FilmeResponseDTO> filme;
     private List<SerieResponseDTO> serie;

}



