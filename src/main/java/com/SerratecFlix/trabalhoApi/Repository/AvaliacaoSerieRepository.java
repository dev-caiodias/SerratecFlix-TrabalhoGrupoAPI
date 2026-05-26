package com.SerratecFlix.trabalhoApi.Repository;

import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoSerie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoSerieRepository extends JpaRepository<AvaliacaoSerie, Long> {

@org.springframework.data.jpa.repository.Query("SELECT AVG(a.nota) FROM AvaliacaoSerie a WHERE a.serie.id = :serieId")
Double calcularMediaPorSerieId(@org.springframework.data.repository.query.Param("serieId") Long serieId);
    
}