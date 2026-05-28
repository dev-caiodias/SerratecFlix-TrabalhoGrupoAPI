package com.SerratecFlix.trabalhoApi.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoSerie;
import com.SerratecFlix.trabalhoApi.Domain.Serie;
import com.SerratecFlix.trabalhoApi.Domain.Usuario;

import com.SerratecFlix.trabalhoApi.Dto.Request.AvaliacaoSerieRequestDTO;
import com.SerratecFlix.trabalhoApi.Dto.Response.AvaliacaoSerieResponseDto;

import com.SerratecFlix.trabalhoApi.Repository.AvaliacaoSerieRepository;
import com.SerratecFlix.trabalhoApi.Repository.SerieRepository;
import com.SerratecFlix.trabalhoApi.Repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliacaoSerieService {

    private final AvaliacaoSerieRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final SerieRepository serieRepository;

    public AvaliacaoSerieResponseDto salvar(
            AvaliacaoSerieRequestDTO dto) {

        Usuario usuario = usuarioRepository
                .findById(dto.getUsuarioId())
                .orElse(null);

        Serie serie = serieRepository
                .findById(dto.getSerieId())
                .orElse(null);

        if (usuario == null || serie == null) {
            return null;
        }

        AvaliacaoSerie avaliacao = new AvaliacaoSerie();

        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());
        avaliacao.setDataAvaliacao(LocalDate.now());
        avaliacao.setUsuario(usuario);
        avaliacao.setSerie(serie);

        AvaliacaoSerie salva = repository.save(avaliacao);

        return converterParaDto(salva);
    }

    public List<AvaliacaoSerieResponseDto> listar() {

        return repository.findAll()
                .stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    public AvaliacaoSerieResponseDto buscarPorId(Long id) {

        AvaliacaoSerie avaliacao =
                repository.findById(id).orElse(null);

        if (avaliacao == null) {
            return null;
        }

        return converterParaDto(avaliacao);
    }

    public AvaliacaoSerieResponseDto atualizar(
            Long id,
            AvaliacaoSerieRequestDTO dto) {

        AvaliacaoSerie avaliacao =
                repository.findById(id).orElse(null);

        if (avaliacao == null) {
            return null;
        }

        Usuario usuario = usuarioRepository
                .findById(dto.getUsuarioId())
                .orElse(null);

        Serie serie = serieRepository
                .findById(dto.getSerieId())
                .orElse(null);

        if (usuario == null || serie == null) {
            return null;
        }

        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());
        avaliacao.setUsuario(usuario);
        avaliacao.setSerie(serie);

        AvaliacaoSerie atualizada =
                repository.save(avaliacao);

        return converterParaDto(atualizada);
    }

    public boolean deletar(Long id) {

        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);

        return true;
    }

    public List<AvaliacaoSerieResponseDto>
            buscarPorNotaMinima(Double nota) {

        return repository.findByNotaGreaterThanEqual(nota)
                .stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    public Double calcularMediaPorSerie(Long serieId) {

        return repository.calcularMediaPorSerieId(serieId);
    }

    private AvaliacaoSerieResponseDto converterParaDto(
            AvaliacaoSerie avaliacao) {

        return new AvaliacaoSerieResponseDto(
                avaliacao.getId(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getDataAvaliacao(),
                avaliacao.getUsuario().getNome(),
                avaliacao.getSerie().getTitulo()
        );
    }
}