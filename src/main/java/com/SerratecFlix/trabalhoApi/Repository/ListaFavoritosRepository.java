package com.SerratecFlix.trabalhoApi.Repository;


import com.SerratecFlix.trabalhoApi.Domain.ListaFavoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaFavoritosRepository extends JpaRepository<ListaFavoritos, Long> {

}
