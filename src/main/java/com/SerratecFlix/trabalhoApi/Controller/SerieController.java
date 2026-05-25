package com.SerratecFlix.trabalhoApi.Controller;

import com.SerratecFlix.trabalhoApi.Dto.Request.SerieRequestDTO;
import com.SerratecFlix.trabalhoApi.Dto.Response.SerieResponseDTO;
import com.SerratecFlix.trabalhoApi.Service.SerieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public ResponseEntity<List<SerieResponseDTO>> obterTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<SerieResponseDTO>> obterPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(service.buscarPorTitulo(titulo));
    }

    @PostMapping
    public ResponseEntity<SerieResponseDTO> criar(@Valid @RequestBody SerieRequestDTO dto) {
        SerieResponseDTO novoDto = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody SerieRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}