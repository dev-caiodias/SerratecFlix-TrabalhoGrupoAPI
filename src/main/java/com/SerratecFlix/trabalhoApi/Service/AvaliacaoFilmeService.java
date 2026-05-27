package com.SerratecFlix.trabalhoApi.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.SerratecFlix.trabalhoApi.Domain.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoFilme;
import com.SerratecFlix.trabalhoApi.Domain.Filme;
import com.SerratecFlix.trabalhoApi.Domain.Usuario;
import com.SerratecFlix.trabalhoApi.Dto.Request.AvaliacaoFilmeDTOResquest;
import com.SerratecFlix.trabalhoApi.Dto.Response.AvaliacaoFilmeDTOResponse;
import com.SerratecFlix.trabalhoApi.Repository.AvaliacaoFilmeRepository;

import jakarta.transaction.Transactional;

@Service
public class AvaliacaoFilmeService {

    @Autowired
    private AvaliacaoFilmeRepository avaliacaoFilmeRepository;

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private UsuarioService usuarioService;

    /*Get para listar todas as avaliações de filmes */
    public List<AvaliacaoFilmeDTOResponse> listarTodos() {
        return avaliacaoFilmeRepository.findAll()
                .stream()
                .map(this :: converterParaResponse)
                .collect(Collectors.toList());
    }

    /*GET para buscar pelo ID da avaliacao */
    public AvaliacaoFilmeDTOResponse buscarPorId(Long id){
        AvaliacaoFilme avaliacao  = avaliacaoFilmeRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada."));
        return converterParaResponse(avaliacao);
    }

    /*Buscar avaliação do filme pelo ID do filme */
    public List<AvaliacaoFilmeDTOResponse> buscarPorFilme(Long filmeId) {
        return avaliacaoFilmeRepository.findByFilmeId(filmeId)
                        .stream()
                        .map(this :: converterParaResponse)
                        .collect(Collectors.toList());
    }

    /*Post para registro de avaliações */
    @Transactional
    public AvaliacaoFilmeDTOResponse criar(AvaliacaoFilmeDTOResquest request) {
        /* Verifica se já existe o devido usuario e filme*/
        Filme filme = filmeService.buscarDomainPorId(request.getFilmeId());
        Usuario usuario = usuarioService.buscarDomainPorId(request.getUsuarioId());

        AvaliacaoFilme avaliacaoFilme = new AvaliacaoFilme();
        avaliacaoFilme.setNota(request.getNota());
        avaliacaoFilme.setComentario(request.getComentario());
        avaliacaoFilme.setDataAvaliacao(LocalDateTime.now());
        avaliacaoFilme.setFilme(filme);
        avaliacaoFilme.setUsuario(usuario);

        avaliacaoFilme = avaliacaoFilmeRepository.save(avaliacaoFilme);

        filmeService.recalcularNotaMedia(filme.getId());

        return converterParaResponse(avaliacaoFilme);
    }

    /*PUT - atualizar informações da avaliação */
    @Transactional
    public AvaliacaoFilmeDTOResponse atualizar(Long id, AvaliacaoFilmeDTOResquest request) {
        AvaliacaoFilme avaliacaoFilme = avaliacaoFilmeRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada."));
        
        /*Atualização caso mude os dados do filme, precisamos recalcular */
        Long antigoFilmeId = avaliacaoFilme.getFilme().getId();

        Filme novoFilme = filmeService.buscarDomainPorId(request.getFilmeId());
        Usuario usuario = usuarioService.buscarDomainPorId(request.getUsuarioId());

        avaliacaoFilme.setNota(request.getNota());
        avaliacaoFilme.setComentario(request.getComentario());
        avaliacaoFilme.setFilme(novoFilme);
        avaliacaoFilme.setUsuario(usuario);

        avaliacaoFilme = avaliacaoFilmeRepository.save(avaliacaoFilme);

        /*Recalculando a média do filme atual */
        filmeService.recalcularNotaMedia(novoFilme.getId());

        /*Recalculando a nota do filme antigo, se ele foi alterado no PUT */
        if (!antigoFilmeId.equals(novoFilme.getId())) {
            filmeService.recalcularNotaMedia(antigoFilmeId);
        }

        return converterParaResponse(avaliacaoFilme);
    }

    /*DELETE - deleta a avaliação e recalcula a nota */
    @Transactional
    public void deletar(Long id) {
        AvaliacaoFilme avaliacaoFilme = avaliacaoFilmeRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada."));

                        Long filmeId = avaliacaoFilme.getFilme().getId();

                        avaliacaoFilmeRepository.delete(avaliacaoFilme);

                        filmeService.recalcularNotaMedia(filmeId);
    }

    /*Método de conversão de dtos request para response */
    private AvaliacaoFilmeDTOResponse converterParaResponse(AvaliacaoFilme avaliacao) {
        AvaliacaoFilmeDTOResponse response = new AvaliacaoFilmeDTOResponse();
        response.setId(avaliacao.getId());
        response.setNota(avaliacao.getNota());
        response.setComentario(avaliacao.getComentario());
        response.setDataAvaliacao(avaliacao.getDataAvaliacao());
        
        /* Evita NullPointerException se o relacionamento estiver nulo*/
        if (avaliacao.getUsuario() != null) {
            response.setNomeUsuario(avaliacao.getUsuario().getNome()); /*Ajuste para o método real de nome em Usuario*/
        }
        if (avaliacao.getFilme() != null) {
            response.setTituloFilme(avaliacao.getFilme().getTitulo()); /*Ajuste para o método real de título em Filme */
        }
        
        return response;
    }
}
