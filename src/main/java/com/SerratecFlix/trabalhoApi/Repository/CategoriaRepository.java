package com.SerratecFlix.trabalhoApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SerratecFlix.trabalhoApi.Domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}

