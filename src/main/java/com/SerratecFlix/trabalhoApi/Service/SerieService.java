package com.SerratecFlix.trabalhoApi.Service;

import com.SerratecFlix.trabalhoApi.Repository.AvaliacaoSerieRepository;
import com.SerratecFlix.trabalhoApi.Domain.Serie;
import com.SerratecFlix.trabalhoApi.Domain.Categoria;
import com.SerratecFlix.trabalhoApi.Dto.Request.SerieRequestDTO;
import com.SerratecFlix.trabalhoApi.Dto.Response.SerieResponseDTO;
import com.SerratecFlix.trabalhoApi.Dto.SerieStatsResponse;
import com.SerratecFlix.trabalhoApi.Repository.SerieRepository;
import com.SerratecFlix.trabalhoApi.Repository.CategoriaRepository;
import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoSerie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor 
public class SerieService {
    
    private final SerieRepository serieRepository;
    private final CategoriaRepository categoriaRepository;
    private final AvaliacaoSerieRepository avaliacaoSerieRepository;
    private final TmdbService tmdbService;

    @Transactional(readOnly = true)
    public List<SerieResponseDTO> listarTodas() {
        return serieRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SerieResponseDTO buscarPorId(Long id) {
        Serie serie = serieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Série não localizada."));
        return converterParaDTO(serie);
    }

    @Transactional(readOnly = true)
    public List<SerieResponseDTO> buscarPorTitulo(String titulo) {
        return serieRepository.findByTituloContainingIgnoreCase(titulo).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public SerieResponseDTO salvar(SerieRequestDTO request) {
        List<Categoria> categorias = categoriaRepository.findAllById(request.getCategoriaIds());
        if (categorias.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pelo menos uma categoria válida deve ser informada.");
        }

        Serie serie = Serie.builder()
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .temporadas(request.getTemporadas())
                .episodios(request.getEpisodios())
                .dataLancamento(request.getDataLancamento())
                .categorias(categorias)
                .notaMedia(0.0)
                .build();

        return converterParaDTO(serieRepository.save(serie));
    }

    @Transactional
    public SerieResponseDTO atualizar(Long id, SerieRequestDTO request) {
        Serie serieExistente = serieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Série não localizada para atualização."));

        List<Categoria> categorias = categoriaRepository.findAllById(request.getCategoriaIds());
        if (categorias.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pelo menos uma categoria válida deve ser informada.");
        }

        serieExistente.setTitulo(request.getTitulo());
        serieExistente.setDescricao(request.getDescricao());
        serieExistente.setTemporadas(request.getTemporadas());
        serieExistente.setEpisodios(request.getEpisodios());
        serieExistente.setDataLancamento(request.getDataLancamento());
        serieExistente.setCategorias(categorias);

        return converterParaDTO(serieRepository.save(serieExistente));
    }

    @Transactional
    public void deletar(Long id) {
        if (!serieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Série não localizada para exclusão.");
        }
        serieRepository.deleteById(id);
    }

    @Transactional
    public void recalcularNotaMedia(Long serieId) {
        Serie serie = serieRepository.findById(serieId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Série não localizada para recalcular média."));
        
        Double mediaCalculada = avaliacaoSerieRepository.calcularMediaPorSerieId(serieId);
      
        serie.setNotaMedia(mediaCalculada != null ? mediaCalculada : 0.0);
        serieRepository.save(serie);
    }

    @Transactional(readOnly = true)
    public SerieStatsResponse obterEstatisticas(Long id) {
        Serie serie = serieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Série não localizada."));

        var listaAvaliacoes = serie.getAvaliacoes();
        long total = listaAvaliacoes != null ? listaAvaliacoes.size() : 0;

        double max = total > 0 ? listaAvaliacoes.stream().mapToDouble(AvaliacaoSerie::getNota).max().orElse(0.0) : 0.0;
        double min = total > 0 ? listaAvaliacoes.stream().mapToDouble(AvaliacaoSerie::getNota).min().orElse(0.0) : 0.0;
        double mediaGlobal = serie.getNotaMedia() != null ? serie.getNotaMedia() : 0.0;

        double mediaEpisodiosPorTemporada = 0.0;
        if (serie.getTemporadas() != null && serie.getTemporadas() > 0 && serie.getEpisodios() != null) {
            mediaEpisodiosPorTemporada = (double) serie.getEpisodios() / serie.getTemporadas();
        }

        return SerieStatsResponse.builder()
                .serieId(serie.getId())
                .titulo(serie.getTitulo())
                .totalAvaliacoes(total)
                .notaMediaGlobal(mediaGlobal)
                .notaMaxima(max)
                .notaMinima(min)
                .mediaEpisodiosPorTemporada(mediaEpisodiosPorTemporada)
                .build();
    }

    private SerieResponseDTO converterParaDTO(Serie serie) {
        List<String> nomesCategorias = serie.getCategorias() != null ?
                serie.getCategorias().stream().map(Categoria::getNome).collect(Collectors.toList()) : List.of();

        return SerieResponseDTO.builder()
                .id(serie.getId())
                .titulo(serie.getTitulo())
                .descricao(serie.getDescricao())
                .temporadas(serie.getTemporadas())
                .episodios(serie.getEpisodios())
                .dataLancamento(serie.getDataLancamento())
                .notaMedia(serie.getNotaMedia())
                .nomesCategorias(nomesCategorias)
                .build();
    }
    
    @Transactional(readOnly = true)
    public Double obterMedia(Long idSerie) {
        Double media = avaliacaoSerieRepository.calcularMediaPorSerieId(idSerie);
        return media != null ? media : 0.0;
    }
}