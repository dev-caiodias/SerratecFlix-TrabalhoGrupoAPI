package com.SerratecFlix.trabalhoApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SerratecFlix.trabalhoApi.Domain.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT f FROM Filme f WHERE f.notaMedia >= :nota")
    List<Filme> findByNotaMediaMinima(@Param("nota") Double nota);

    @Query("SELECT f FROM Filme f WHERE f.notaMedia IS NOT NULL ORDER BY f.notaMedia DESC")
    Page<Filme> findRankingGeral(Pageable pageable);

    @Query("SELECT f FROM Filme f JOIN f.categorias c WHERE c.id = :categoriaId AND f.notaMedia IS NOT NULL ORDER BY f.notaMedia DESC")
    Page<Filme> findRankingPorCategoria(@Param("categoriaId") Long categoriaId, Pageable pageable);

}
