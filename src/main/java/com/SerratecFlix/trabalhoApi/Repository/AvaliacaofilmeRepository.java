package com.SerratecFlix.trabalhoApi.Repository;

import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoFilme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvaliacaoFilmeRepository extends JpaRepository<AvaliacaoFilme, Long> {
    @Query("SELECT AVG(a.id) FROM AvaliacaoFilme a WHERE a.filme.id = :filmeId") 
    Double calcularMediaPorFilmeId(@Param("filmeId") Long filmeId);
}

//Classe temporaria pra testes