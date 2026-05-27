package com.SerratecFlix.trabalhoApi.Dto.Request;

import lombok.Data;

@Data
public class CatalogoFiltroRequest {
    private String categoria;
    private String tipo;
    private Double notaMinima;
    private Integer page = 0;
    private Integer size = 10;
}
