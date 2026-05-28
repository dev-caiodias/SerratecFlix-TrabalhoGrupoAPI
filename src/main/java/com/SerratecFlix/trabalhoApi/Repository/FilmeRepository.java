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

    @Query("SELECT DISTINCT f FROM Filme f " +
            " JOIN f.categorias c " +
            " WHERE c.id IN (" +
            "   SELECT c2.id FROM AvaliacaoFilme a " +
            "   JOIN a.filme f2 " +
            "   JOIN f2.categorias c2 " +
            "   WHERE a.usuario.id = :usuarioId AND a.nota >= :notaMinima " +
            ") " +
            "AND f.id NOT IN (" +
            "   SELECT a2.filme.id FROM AvaliacaoFilme a2 " +
            "   WHERE a2.usuario.id = :usuarioId" +
            ")"
        )
        List<Filme> buscarRecomendacoes(@Param("usuarioId") Long usuarioId, @Param("notaMinima") Double notaMinima);
}
