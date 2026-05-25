package com.SerratecFlix.trabalhoApi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SerratecFlix.trabalhoApi.Dto.Request.AvaliacaoFilmeDTOResquest;
import com.SerratecFlix.trabalhoApi.Dto.Response.AvaliacaoFilmeDTOResponse;
import com.SerratecFlix.trabalhoApi.Service.AvaliacaoFilmeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/avaliacoes-filme")
public class AvaliacaoFilmeController {

    @Autowired
    private AvaliacaoFilmeService avaliacaoFilmeService;

    /*GET listar todos */
    @GetMapping
    public ResponseEntity<List<AvaliacaoFilmeDTOResponse>> listarTodas() {
        List<AvaliacaoFilmeDTOResponse> lista = avaliacaoFilmeService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    /*GET por ID */
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoFilmeDTOResponse> buscarPorId(@PathVariable Long id) {
        AvaliacaoFilmeDTOResponse response = avaliacaoFilmeService.buscarPorId(id);
        return ResponseEntity.ok(response); 
    }

    /*GEt pelo id do filme */
    @GetMapping("/filme/{filmeId}")
    public ResponseEntity<List<AvaliacaoFilmeDTOResponse>> buscarPorFilme(@PathVariable Long filmeId) {
        List<AvaliacaoFilmeDTOResponse> lista = avaliacaoFilmeService.buscarPorFilme(filmeId);
        return ResponseEntity.ok(lista);
    }
    
    /*POST */
    @PostMapping
    public ResponseEntity<AvaliacaoFilmeDTOResponse> criar(@Valid @RequestBody AvaliacaoFilmeDTOResquest request) {
        AvaliacaoFilmeDTOResponse response = avaliacaoFilmeService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*DELETe */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        avaliacaoFilmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
