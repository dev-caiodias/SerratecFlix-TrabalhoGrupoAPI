package com.SerratecFlix.trabalhoApi.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CatalogoItemResponse {

    private Long id;
    private String titulo;
    private String tipo;
    private Double notaMedia;
    private String classificacao;
    private List<String> categorias;
}
