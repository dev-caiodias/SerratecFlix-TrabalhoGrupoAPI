package com.SerratecFlix.trabalhoApi.Service;


import com.SerratecFlix.trabalhoApi.Domain.ListaFavoritos;
import com.SerratecFlix.trabalhoApi.Repository.ListaFavoritosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListaFavoritosService {

    @Autowired
    private ListaFavoritosRepository listaFavoritosRepository;

    public ListaFavoritos buscarPorId(Long id) {
        ListaFavoritos listaFavoritos = listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
                return new ListaFavoritos(listaFavoritos);
    }
}
