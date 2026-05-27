package com.SerratecFlix.trabalhoApi.Repository;
// Calcula a media da avaliacoes da serie e mostra as series com avaliacao acima de um valor >=
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoSerie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoSerieRepository extends JpaRepository<AvaliacaoSerie, Long> {

    List<AvaliacaoSerie> findByNotaGreaterThanEqual(Double nota);
    @Query("SELECT AVG(m.nota) FROM AvaliacaoSerie m WHERE m.serie.id = :idSerie")
    Double calcularMedia(Long idSerie);

@org.springframework.data.jpa.repository.Query("SELECT AVG(a.nota) FROM AvaliacaoSerie a WHERE a.serie.id = :serieId")
Double calcularMediaPorSerieId(@org.springframework.data.repository.query.Param("serieId") Long serieId);
    
}