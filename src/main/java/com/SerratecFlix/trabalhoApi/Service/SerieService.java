package com.SerratecFlix.trabalhoApi.Service;

import com.SerratecFlix.trabalhoApi.Repository.AvaliacaoSerieRepository;
import com.SerratecFlix.trabalhoApi.Domain.Serie;
import com.SerratecFlix.trabalhoApi.Dto.Request.SerieRequestDTO;
import com.SerratecFlix.trabalhoApi.Dto.Response.SerieResponseDTO;
import com.SerratecFlix.trabalhoApi.Repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {
	
	@Autowired
	private AvaliacaoSerieRepository avaliacaoRepository;

    @
    Autowired
    private SerieRepository repository;

    public List<SerieResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(SerieResponseDTO::new)
                .collect(Collectors.toList());
    }

    public SerieResponseDTO buscarPorId(Long id) {
        Serie serie = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Série com ID " + id + " não encontrada."));
        return new SerieResponseDTO(serie);
    }

    public SerieResponseDTO salvar(SerieRequestDTO dto) {
        Serie serie = new Serie();
        copiarDtoParaEntidade(dto, serie);
        serie = repository.save(serie);
        return new SerieResponseDTO(serie);
    }

    public SerieResponseDTO atualizar(Long id, SerieRequestDTO dto) {
        Serie serie = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Série com ID " + id + " não encontrada."));
        copiarDtoParaEntidade(dto, serie);
        serie = repository.save(serie);
        return new SerieResponseDTO(serie);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Série com ID " + id + " não encontrada.");
        }
        repository.deleteById(id);
    }

    public List<SerieResponseDTO> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo).stream()
                .map(SerieResponseDTO::new)
                .collect(Collectors.toList());
    }

    private void copiarDtoParaEntidade(SerieRequestDTO dto, Serie entidade) {
        entidade.setTitulo(dto.getTitulo());
        entidade.setDescricao(dto.getDescricao());
        entidade.setTemporadas(dto.getTemporadas());
        entidade.setEpisodios(dto.getEpisodios());
        entidade.setDataLancamento(dto.getDataLancamento());
    }
    
    public Double obterMedia(Long idSerie) {
        return avaliacaoRepository.calcularMedia(idSerie);
    }
}
