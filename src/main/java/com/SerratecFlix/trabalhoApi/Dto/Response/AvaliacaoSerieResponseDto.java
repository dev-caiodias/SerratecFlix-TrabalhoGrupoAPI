package com.SerratecFlix.trabalhoApi.Dto.Response;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoSerieResponseDto {

    private Long id;
    private Double nota;
    private String comentario;
    private LocalDate dataAvaliacao;

    private String usuarioNome;
    private String serieTitulo;
}