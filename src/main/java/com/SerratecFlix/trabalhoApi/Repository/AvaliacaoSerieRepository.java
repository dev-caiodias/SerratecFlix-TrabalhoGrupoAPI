package com.SerratecFlix.trabalhoApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoSerie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoSerieRepository extends JpaRepository<AvaliacaoSerie, Long> {

<<<<<<< HEAD
    List<AvaliacaoSerie> findByNotaGreaterThanEqual(Double nota);
    @Query("SELECT AVG(m.nota) FROM AvaliacaoSerie m WHERE m.serie.id = :idSerie")
    Double calcularMedia(Long idSerie);

=======
@org.springframework.data.jpa.repository.Query("SELECT AVG(a.nota) FROM AvaliacaoSerie a WHERE a.serie.id = :serieId")
Double calcularMediaPorSerieId(@org.springframework.data.repository.query.Param("serieId") Long serieId);
    
>>>>>>> c36d67ed2cc94d266fa452d969a8187058c3785b
}