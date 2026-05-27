package com.SerratecFlix.trabalhoApi.Repository;

import com.SerratecFlix.trabalhoApi.Domain.Serie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
 
    List<Serie> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT s FROM Serie s WHERE s.notaMedia >= :notaMinima")
    List<Serie> buscarPorNotaMinima(@Param("notaMinima") Double notaMinima);

    @Query(value = "SELECT * FROM tb_serie WHERE temporadas >= :temporadasMinimas", nativeQuery = true)
    List<Serie> buscarPorTemporadasMinimas(@Param("temporadasMinimas") Integer temporadasMinimas);

    @Query("""
    SELECT DISTINCT s FROM Serie s
    JOIN s.categorias c
    WHERE (:categoria IS NULL OR LOWER(c.nome) = LOWER(:categoria))
    AND (:notaMinima IS NULL OR s.notaMedia >= :notaMinima)
    """)
    Page<Serie> filtrarCatalogo(@Param("categoria") String categoria,
                                @Param("notaMinima") Double notaMinima,
                                Pageable pageable);
}