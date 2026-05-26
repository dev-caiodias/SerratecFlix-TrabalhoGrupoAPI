package com.SerratecFlix.trabalhoApi.Controller;

import com.SerratecFlix.trabalhoApi.Dto.Request.FilmeRequestDTO;
import com.SerratecFlix.trabalhoApi.Dto.Response.FilmeResponseDTO;
import com.SerratecFlix.trabalhoApi.Service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
@Tag(name = "Filmes", description = "Endpoints de gerenciamento dos filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    @Operation(summary = "Listar todos os filmes")
    public ResponseEntity<List<FilmeResponseDTO>> listarTodos() {
        return ResponseEntity.ok(filmeService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar filme por ID")
    public ResponseEntity<FilmeResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(filmeService.buscarPorIdDTO(id));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar filmes por parte do título")
    public ResponseEntity<List<FilmeResponseDTO>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(filmeService.buscarPorTitulo(titulo));
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo filme")
    public ResponseEntity<FilmeResponseDTO> criar(@Valid @RequestBody FilmeRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de um filme")
    public ResponseEntity<FilmeResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FilmeRequestDTO request) {
        return ResponseEntity.ok(filmeService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um filme do catálogo")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}