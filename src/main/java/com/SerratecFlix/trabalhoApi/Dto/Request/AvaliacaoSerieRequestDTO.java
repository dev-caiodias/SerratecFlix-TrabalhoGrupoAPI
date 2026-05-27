package com.SerratecFlix.trabalhoApi.Dto.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoSerieRequestDto {

    @NotNull
    @Min(0)
    @Max(10)
    private Double nota;

    @NotBlank
    private String comentario;

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long serieId;
}