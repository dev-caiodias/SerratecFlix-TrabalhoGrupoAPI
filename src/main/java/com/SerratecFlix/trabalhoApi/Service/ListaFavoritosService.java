package com.SerratecFlix.trabalhoApi.Service;


import com.SerratecFlix.trabalhoApi.Domain.Filme;
import com.SerratecFlix.trabalhoApi.Domain.ListaFavoritos;
import com.SerratecFlix.trabalhoApi.Domain.Serie;
import com.SerratecFlix.trabalhoApi.Domain.Usuario;
import com.SerratecFlix.trabalhoApi.Dto.Request.ListaFavoritosDTORequest;
import com.SerratecFlix.trabalhoApi.Dto.Response.ListaFavoritosDTOResponse;
import com.SerratecFlix.trabalhoApi.Exception.ResourceNotFoundException;
import com.SerratecFlix.trabalhoApi.Repository.FilmeRepository;
import com.SerratecFlix.trabalhoApi.Repository.ListaFavoritosRepository;
import com.SerratecFlix.trabalhoApi.Repository.SerieRepository;
import com.SerratecFlix.trabalhoApi.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListaFavoritosService {

    @Autowired
    private ListaFavoritosRepository listaFavoritosRepository;
    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<ListaFavoritosDTOResponse> listar() {
        return listaFavoritosRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public ListaFavoritosDTOResponse buscarPorId(Long id) {
        ListaFavoritos listaFavoritos = listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
        return new ListaFavoritos(listaFavoritos);
    }


    public ListaFavoritosDTOResponse criar(ListaFavoritosDTORequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));

        ListaFavoritos listaFavoritos = ListaFavoritos.builder()
                .favoritos(request.getFavoritos())
                .privada(request.getPrivada())
                .usuario(usuario)
                .build();

        return toResponse(listaFavoritosRepository.save(listaFavoritos));
    }

    public ListaFavoritosDTOResponse atualizar(ListaFavoritosDTORequest request, Long id) {
        ListaFavoritos listaFavoritos = listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada."));


        listaFavoritos.setFavoritos(request.getFavoritos());
        listaFavoritos.setFilme(request.getFilme());
        listaFavoritos.setSerie(request.getSerie());

        return toResponse(listaFavoritosRepository.save(listaFavoritos));
    }

    public ListaFavoritos adicionaFilme(Long id, Long filmeId) {
        ListaFavoritos listaFavoritos = listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada."));

        Filme filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado."));

        if (listaFavoritos.getFilme().contains(filme)) {
            throw new RuntimeException("Filme ja está na lista");
        }

        listaFavoritos.adicionaFilme(filme);
        return listaFavoritosRepository.save(listaFavoritos);
    }


    public ListaFavoritos adicionaSerie(Long id, Long serieId) {
        ListaFavoritos listaFavoritos = listaFavoritosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada."));

        Serie serie = serieRepository.findById(serieId)
                .orElseThrow(() -> new ResourceNotFoundException("Serie não encontrado."));

        if (listaFavoritos.getSerie().contains(serie)) {
            throw new RuntimeException("Serie ja está na lista");
        }

        listaFavoritos.adicionaSerie(serie);
        return listaFavoritosRepository.save(listaFavoritos);
    }



    public void deletar(Long id) {
        if (!listaFavoritosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }
        listaFavoritosRepository.deleteById(id);
    }

    private ListaFavoritosDTOResponse toResponse(ListaFavoritos listaFavoritos) {

//        List<FilmeResponseDTO> filmes = listaFavoritos.getFilme();
//        List<SerieResponseDTO> series = listaFavoritos.getSerie();


        return ListaFavoritosDTOResponse.builder()
                .id(listaFavoritos.getId())
                .favoritos(listaFavoritos.getFavoritos())
                .dataCriacao(listaFavoritos.getDataCriacao())
                .nomeUsuario(listaFavoritos.getUsuario().getNome())
                .build();
    }
}