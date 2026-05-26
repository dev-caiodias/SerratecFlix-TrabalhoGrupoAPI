package com.SerratecFlix.trabalhoApi.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SerieStatsResponse {
    private Long serieId;
    private String titulo;
    private Long totalAvaliacoes;
    private Double notaMediaGlobal;
    private Double notaMaxima;
    private Double notaMinima;
    private Double mediaEpisodiosPorTemporada;
}