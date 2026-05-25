package com.SerratecFlix.trabalhoApi.Controller;

import java.util.List;

import com.SerratecFlix.trabalhoApi.Domain.AvaliacaoSerie;
import com.SerratecFlix.trabalhoApi.Service.AvaliacaoSerieService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes-series")
public class AvaliacaoSerieController {

    @Autowired
    private AvaliacaoSerieService service;

    @PostMapping
    public ResponseEntity<AvaliacaoSerie> salvar(@RequestBody AvaliacaoSerie avaliacao) {

        AvaliacaoSerie nova = service.salvar(avaliacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoSerie>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoSerie> buscar(@PathVariable Long id) {

        AvaliacaoSerie avaliacao = service.buscarPorId(id);

        if (avaliacao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(avaliacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}