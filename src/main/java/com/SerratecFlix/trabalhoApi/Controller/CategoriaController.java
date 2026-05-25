package com.SerratecFlix.trabalhoApi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SerratecFlix.trabalhoApi.Dto.Request.CategoriaDTORequest;
import com.SerratecFlix.trabalhoApi.Dto.Response.CategoriaDTOResponse;
import com.SerratecFlix.trabalhoApi.Service.CategoriaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTOResponse>> listarTodas() {
        List<CategoriaDTOResponse> lista = categoriaService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTOResponse> buscarPorId(@PathVariable Long id) {
        CategoriaDTOResponse response = categoriaService.buscaPorId(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTOResponse> criar(@Valid @RequestBody CategoriaDTORequest dto) {
        CategoriaDTOResponse response = categoriaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTOResponse> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaDTORequest dto) {
        CategoriaDTOResponse response = categoriaService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
