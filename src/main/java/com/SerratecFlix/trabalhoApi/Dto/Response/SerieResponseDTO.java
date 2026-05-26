package com.SerratecFlix.trabalhoApi.Dto.Response;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SerieResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Integer temporadas;
    private Integer episodios;
    private LocalDate dataLancamento;
    private Double notaMedia;
    private List<String> nomesCategorias;
}