package com.SerratecFlix.trabalhoApi.Service;

import com.SerratecFlix.trabalhoApi.Domain.Categoria;
import com.SerratecFlix.trabalhoApi.Domain.Filme;
import com.SerratecFlix.trabalhoApi.Domain.Serie;
import com.SerratecFlix.trabalhoApi.Dto.Response.CatalogoItemResponse;
import com.SerratecFlix.trabalhoApi.Repository.FilmeRepository;
import com.SerratecFlix.trabalhoApi.Repository.SerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogoService {

    private final FilmeRepository filmeRepository;
    private final SerieRepository serieRepository;

    public Page<CatalogoItemResponse> descobrir (String categoria,
                                                 String tipo,
                                                 Double notaMinima,
                                                 Pageable pageable){
        List<CatalogoItemResponse> resultados = new ArrayList<>();

        if (tipo == null || tipo.equalsIgnoreCase("FILME")){
            filmeRepository.filtrarCatalogo(categoria, notaMinima, pageable)
                    .forEach(f -> resultados.add(toFilmeResponse(f)));
        }

        if (tipo == null || tipo.equalsIgnoreCase(("SERIE"))){
            serieRepository.filtrarCatalogo(categoria, notaMinima, pageable)
                    .forEach(s -> resultados.add(toSerieResponse(s)));
        }

        resultados.sort(Comparator.comparingDouble(CatalogoItemResponse::getNotaMedia).reversed());

        int inicio = (int) pageable.getOffset();
        int fim = Math.min(inicio + pageable.getPageSize(), resultados.size());
        List <CatalogoItemResponse> paginado = resultados.subList(inicio,fim);

        return new PageImpl<>(paginado, pageable, resultados.size());
    }

    private CatalogoItemResponse toFilmeResponse(Filme f) {
        return CatalogoItemResponse.builder()
                .id(f.getId())
                .titulo(f.getTitulo())
                .tipo("FILME")
                .notaMedia(f.getNotaMedia())
                .classificacao(f.getClassificacaoIndicativa().name())
                .categorias(f.getCategorias().stream().map(Categoria::getNome).toList())
                .build();
    }

    private CatalogoItemResponse toSerieResponse(Serie s) {
        return CatalogoItemResponse.builder()
                .id(s.getId())
                .titulo(s.getTitulo())
                .tipo("SERIE")
                .notaMedia(s.getNotaMedia())
                .categorias(s.getCategorias().stream().map(Categoria::getNome).toList())
                .build();
    }
}
