package com.SerratecFlix.trabalhoApi.Service;


import com.SerratecFlix.trabalhoApi.Domain.ListaFavoritos;
import com.SerratecFlix.trabalhoApi.Domain.Serie;
import com.SerratecFlix.trabalhoApi.Dto.Request.ListaFavoritosDTORequest;
import com.SerratecFlix.trabalhoApi.Dto.Response.ListaFavoritosDTOResponse;
import com.SerratecFlix.trabalhoApi.Repository.FilmeRepository;
import com.SerratecFlix.trabalhoApi.Repository.ListaFavoritosRepository;
import com.SerratecFlix.trabalhoApi.Repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaFavoritosService {

    @Autowired
    private ListaFavoritosRepository listaFavoritosRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private SerieRepository serieRepository;


    public List<ListaFavoritosDTOResponse> listar(){
        return listaFavoritosRepository.findAll();
    }


    public ListaFavoritosDTOResponse buscarPorId(Long id) {
        ListaFavoritos listaFavoritos = listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
                return new ListaFavoritos(listaFavoritos);
    }


    public ListaFavoritosDTOResponse criar(ListaFavoritosDTORequest request){

        ListaFavoritos listaFavoritos = ListaFavoritos.builder()
                .favoritos(request.getFavoritos())
                .privada(request.getPrivada())
                .build();

        return new ListaFavoritos(listaFavoritosRepository.save(listaFavoritos));
    }


    public ListaFavoritosDTOResponse atualizar(ListaFavoritosDTORequest request, Long id){
        ListaFavoritos listaFavoritos = listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada."));


        listaFavoritos.setFavoritos(request.getFavoritos());
        listaFavoritos.setFilme(request.getFilme);
        listaFavoritos.setSerie(request.getSerie);

        return atualizar(listaFavoritosRepository.save(listaFavoritos));

    }








    public void deletar(Long id){
        if (!listaFavoritosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }

        listaFavoritosRepository.deleteById(id);
    }



}
