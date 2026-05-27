package com.SerratecFlix.trabalhoApi.Dto.Response;

import com.SerratecFlix.trabalhoApi.Domain.ListaFavoritos;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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



