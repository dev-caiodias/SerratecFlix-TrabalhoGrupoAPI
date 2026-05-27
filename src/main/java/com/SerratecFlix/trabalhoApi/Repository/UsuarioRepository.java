package com.SerratecFlix.trabalhoApi.Repository;

import com.SerratecFlix.trabalhoApi.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUserName(String username);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUserName(String username);
}
