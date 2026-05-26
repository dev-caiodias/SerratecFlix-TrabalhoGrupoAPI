package com.SerratecFlix.trabalhoApi.Dto.Response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilmeRankingResponse {
    @Schema(description = "Posição do filme no ranking", example = "1")
    private Integer posicao;

    @Schema(description = "Dados do filme")
    private FilmeResponseDTO filme;

    @Schema(description = "Total de avaliações que o filme recebeu", example = "42")
    private Integer totalAvaliacoes;
}
