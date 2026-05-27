package com.SerratecFlix.trabalhoApi.Service;

import java.time.LocalDate;
import java.util.List;

import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoSerie;
import com.SerratecFlix.trabalhoApi.Repository.AvaliacaoSerieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoSerieService {

    @Autowired
    private AvaliacaoSerieRepository repository;

    public AvaliacaoSerie salvar(AvaliacaoSerie avaliacao) {

        avaliacao.setDataAvaliacao(LocalDate.now());

        return repository.save(avaliacao);
    }

    public List<AvaliacaoSerie> listar() {
        return repository.findAll();
    }

    public AvaliacaoSerie buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<AvaliacaoSerie> buscarPorNotaMinima(Integer nota) {
        return repository.findByNotaGreaterThanEqual(nota);
    }
}