package com.SerratecFlix.trabalhoApi.Service;

import com.SerratecFlix.trabalhoApi.Domain.Filme;
import com.SerratecFlix.trabalhoApi.Domain.Categoria;
import com.SerratecFlix.trabalhoApi.Dto.Request.FilmeRequestDTO;
import com.SerratecFlix.trabalhoApi.Dto.Response.FilmeResponseDTO;
import com.SerratecFlix.trabalhoApi.Repository.FilmeRepository;
import com.SerratecFlix.trabalhoApi.Repository.CategoriaRepository;
import com.SerratecFlix.trabalhoApi.Repository.AvaliacaofilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AvaliacaofilmeRepository avaliacaoFilmeRepository;

    public List<FilmeResponseDTO> listarTodos() {
        return filmeRepository.findAll().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    public Filme findByFilmeId(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado"));
    }

    public FilmeResponseDTO buscarPorIdDTO(Long id) {
        return converterParaResponse(findByFilmeId(id));
    }

    public List<FilmeResponseDTO> buscarPorTitulo(String titulo) {
        return filmeRepository.findByTituloContainingIgnoreCase(titulo).stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public FilmeResponseDTO criar(FilmeRequestDTO request) {
        Filme filme = new Filme();
        copiarDtoParaEntidade(request, filme);
        filme.setNotaMedia(0.0);

        return converterParaResponse(filmeRepository.save(filme));
    }

    @Transactional
    public FilmeResponseDTO atualizar(Long id, FilmeRequestDTO request) {
        Filme filme = findByFilmeId(id);
        copiarDtoParaEntidade(request, filme);

        return converterParaResponse(filmeRepository.save(filme));
    }

    @Transactional
    public void deletar(Long id) {
        Filme filme = findByFilmeId(id);
        filmeRepository.delete(filme);
    }

    @Transactional
    public void recalcularNotaMedia(Long filmeId) {
        Filme filme = findByFilmeId(filmeId);

        Double novaMedia = avaliacaoFilmeRepository.calcularMediaPorFilmeId(filmeId);

        filme.setNotaMedia(novaMedia != null ? novaMedia : 0.0);
        filmeRepository.save(filme);
    }

    private void copiarDtoParaEntidade(FilmeRequestDTO dto, Filme filme) {
        filme.setTitulo(dto.getTitulo());
        filme.setDescricao(dto.getDescricao());
        filme.setDuracao(dto.getDuracao());
        filme.setDataLancamento(dto.getDataLancamento());
        filme.setClassificacaoIndicativa(dto.getClassificacaoIndicativa());

        List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriaIds());
        filme.setCategorias(categorias);
    }

    private FilmeResponseDTO converterParaResponse(Filme filme) {
        List<String> nomesCategorias = filme.getCategorias().stream()
                .map(Categoria::getNome)
                .collect(Collectors.toList());

        return FilmeResponseDTO.builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .descricao(filme.getDescricao())
                .duracao(filme.getDuracao())
                .dataLancamento(filme.getDataLancamento())
                .classificacaoIndicativa(filme.getClassificacaoIndicativa().name())
                .notaMedia(filme.getNotaMedia())
                .categorias(nomesCategorias)
                .build();
    }
}
