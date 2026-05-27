package com.SerratecFlix.trabalhoApi.Dto.Request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SerieRequestDTO {

    @NotBlank(message = "O título da série é obrigatório.")
    private String titulo;

    @NotBlank(message = "A descrição da série é obrigatória.")
    private String descricao;

    @NotNull(message = "O número de temporadas é obrigatório.")
    @Positive(message = "O número de temporadas deve ser maior que zero.")
    private Integer temporadas;

    @NotNull(message = "O número total de episódios é obrigatório.")
    @Positive(message = "O número de episódios deve ser maior que zero.")
    private Integer episodios;

    @NotNull(message = "A data de lançamento é obrigatória.")
    private LocalDate dataLancamento;

    @NotEmpty(message = "A série deve pertencer a pelo menos uma categoria.")
    private List<Long> categoriaIds;
}