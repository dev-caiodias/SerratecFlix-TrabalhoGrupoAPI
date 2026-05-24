package com.SerratecFlix.trabalhoApi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SerratecFlix.trabalhoApi.Domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);
}

