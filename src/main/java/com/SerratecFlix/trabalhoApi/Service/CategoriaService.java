package com.SerratecFlix.trabalhoApi.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SerratecFlix.trabalhoApi.Domain.Categoria;
import com.SerratecFlix.trabalhoApi.Dto.Request.CategoriaDTORequest;
import com.SerratecFlix.trabalhoApi.Dto.Response.CategoriaDTOResponse;
import com.SerratecFlix.trabalhoApi.Repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<CategoriaDTOResponse> listarTodas() {
        return categoriaRepository.findAll()
                        .stream()
                        .map(categoria -> new CategoriaDTOResponse(
                            categoria.getId(),
                            categoria.getNome(),
                            categoria.getDescricao()
                        ))
                        .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDTOResponse buscaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com este ID: " + id));
        return new CategoriaDTOResponse(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    @Transactional
    public CategoriaDTOResponse criar(CategoriaDTORequest dto) {
        /*Validação contra duplicações*/
        categoriaRepository.findByNome(dto.getNome()).ifPresent(c -> {
            throw new IllegalStateException("Conflito: Categoria com este nome já existe.");
        });

        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        Categoria salva = categoriaRepository.save(categoria);
        return new CategoriaDTOResponse(salva.getId(), salva.getNome(), salva.getDescricao());
    }

    @Transactional
    public CategoriaDTOResponse atualizar(Long id, CategoriaDTORequest dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + id));

        /*Uso do Optional para garantir que o id e nome não se repita*/
        categoriaRepository.findByNome(dto.getNome())
                    .filter(existente -> !existente.getId().equals(id))
                    .ifPresent(c -> {
                throw new IllegalStateException("Conflito: Outra categoria já utiliza este nome.");
            });

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        Categoria salva = categoriaRepository.save(categoria);

        return new CategoriaDTOResponse(salva.getId(),salva.getNome(),salva.getDescricao());
    }

    @Transactional
    public void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada com ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
