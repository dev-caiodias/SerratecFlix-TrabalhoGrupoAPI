package com.SerratecFlix.trabalhoApi.Repository;
// Calcula a media das avaliacoes da serie e mostra as series com avaliacao acima de um valor >= nota
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoSerie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoSerieRepository extends JpaRepository<AvaliacaoSerie, Long> {

    List<AvaliacaoSerie> findByNotaGreaterThanEqual(Double nota);
    
    @Query("SELECT AVG(m.nota) FROM AvaliacaoSerie m WHERE m.serie.id = :serieId")
    Double calcularMediaPorSerie(@Param("serieId") Long serieId);
    
}
