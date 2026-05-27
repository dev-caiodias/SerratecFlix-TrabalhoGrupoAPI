package com.SerratecFlix.trabalhoApi.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.SerratecFlix.trabalhoApi.Domain.Categoria;
import com.SerratecFlix.trabalhoApi.Domain.Filme;
import com.SerratecFlix.trabalhoApi.Domain.Usuario;
import com.SerratecFlix.trabalhoApi.Dto.Response.FilmeResponse;
import com.SerratecFlix.trabalhoApi.Repository.CategoriaRepository;
import com.SerratecFlix.trabalhoApi.Repository.FilmeRepository;
import com.SerratecFlix.trabalhoApi.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

public class RecomendacaoService {

    @Autowired
    private FilmeRepository filmeRepository;

    private UsuarioRepository usuarioRepository;

    private CategoriaRepository categoriaRepository;

    public List<FilmeResponse> obterRecomendacoes(Long usuarioId, Double notaMinima) {

        /*A nota mínima das recomendações será 7.0, caso o usuario não definir na URI */
        Double notaFiltro = (notaMinima != null) ? notaMinima : 7.0;
        List<Filme> filmes = filmeRepository.buscarRecomendacoes(usuarioId, notaFiltro);
        return filmes.stream().map(filme -> {
            FilmeResponse response = new FilmeResponse();
            response.setId(filme.getId());
            response.setTitulo(filme.getTitulo());
            response.setDuracao(filme.getDuracao());
            response.setDataLancamento(filme.getDataLancamento());
            response.setDescricao(filme.getDescricao());
            response.setNotaMedia(filme.getNotaMedia());
            return response;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void salvarPreferenciaUsuario(Long usuarioId, Long categoriaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Categoria categoria = categoriaRepository.findById(categoriaId)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        
        usuario.getPreferencias().add(categoria);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void removerPreferenciaUsuario(Long usuarioId, Long categoriaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        usuario.getPreferencias().removeIf(cat -> cat.getId().equals(categoriaId));
        usuarioRepository.save(usuario);
    }
}
