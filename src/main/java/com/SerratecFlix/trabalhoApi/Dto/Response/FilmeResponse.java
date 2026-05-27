package com.SerratecFlix.trabalhoApi.Dto.Response;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmeResponse {
    @Schema(description = "ID do filme", example = "1")
    private Long id;

    @Schema(description = "Título do filme", example = "O Senhor dos Anéis: A Sociedade do Anel")
    private String titulo;

    @Schema(description = "Descrição do filme", example = "Um hobbit pacato recebe a missão de destruir um anel maligno.")
    private String descricao;

    @Schema(description = "Duração do filme em minutos", example = "178")
    private Integer duracao;

    @Schema(description = "Data de lançamento do filme", example = "2001-12-19")
    private LocalDate dataLancamento;

    @Schema(description = "Classificação indicativa", example = "DOZE")
    private String classificacaoIndicativa;

    @Schema(description = "Nota média baseada nas avaliações", example = "4.8")
    private Double notaMedia;

    @Schema(description = "Lista de nomes das categorias do filme", example = "[\"Fantasia\", \"Aventura\"]")
    private List<String> categorias;
}
