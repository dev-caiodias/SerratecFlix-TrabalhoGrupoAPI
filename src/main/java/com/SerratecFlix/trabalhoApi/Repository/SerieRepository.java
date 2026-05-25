package com.SerratecFlix.trabalhoApi.Repository;

import com.SerratecFlix.trabalhoApi.Domain.Serie; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface SerieRepository extends JpaRepository<Serie, Long> {
    
    List<Serie> findByTituloContainingIgnoreCase(String titulo);
}