package com.SerratecFlix.trabalhoApi.Dto.Request;

import java.time.LocalDate;
import java.util.List;

import com.SerratecFlix.trabalhoApi.Domain.enums.ClassificacaoIndicativa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FilmeRequest {
    @Schema(description = "Título do filme", example = "O Senhor dos Anéis: A Sociedade do Anel")
    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @Schema(description = "Descrição ou sinopse do filme", example = "Um hobbit pacato recebe a missão de destruir um anel maligno.")
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @Schema(description = "Duração do filme em minutos", example = "178")
    @NotNull(message = "A duração é obrigatória")
    @Positive(message = "A duração deve ser um número positivo")
    private Integer duracao;

    @Schema(description = "Data de lançamento do filme", example = "2001-12-19")
    @NotNull(message = "A data de lançamento é obrigatória")
    private LocalDate dataLancamento;

    @Schema(description = "Classificação indicativa do filme", example = "DOZE")
    @NotNull(message = "A classificação indicativa é obrigatória")
    private ClassificacaoIndicativa classificacaoIndicativa;

    @Schema(description = "Lista de IDs das categorias associadas ao filme", example = "[1, 3]")
    @NotEmpty(message = "O filme deve pertencer a pelo menos uma categoria")
    private List<Long> categoriaIds;
}
