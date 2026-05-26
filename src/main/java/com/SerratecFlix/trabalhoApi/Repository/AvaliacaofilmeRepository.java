package com.SerratecFlix.trabalhoApi.Repository;

import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoFilme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AvaliacaofilmeRepository extends JpaRepository<AvaliacaoFilme, Long> {


    List<AvaliacaoFilme> findByFilmeId(Long filmeId);

    List<AvaliacaoFilme> findByUsuarioId(Long usuarioId);

    @Query("SELECT AVG(a.nota) FROM AvaliacaoFilme a WHERE a.filme.id = :filmeId")
    Optional<Double> calcularMedia(@Param("filmeId") Long filmeId);

    @Query("SELECT AVG(a.id) FROM AvaliacaoFilme a WHERE a.filme.id = :filmeId")
    Double calcularMediaPorFilmeId(@Param("filmeId") Long filmeId);
}

//Classe temporaria pra testes