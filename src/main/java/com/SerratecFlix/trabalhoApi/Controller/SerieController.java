package com.SerratecFlix.trabalhoApi.Controller;

import com.SerratecFlix.trabalhoApi.Dto.Request.SerieRequestDTO;
import com.SerratecFlix.trabalhoApi.Dto.Response.SerieResponseDTO;
import com.SerratecFlix.trabalhoApi.Service.SerieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping
    public ResponseEntity<List<SerieResponseDTO>> listarTodas() {
        return ResponseEntity.ok(serieService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(serieService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<SerieResponseDTO>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(serieService.buscarPorTitulo(titulo));
    }

    @PostMapping
    public ResponseEntity<SerieResponseDTO> cadastrar(@Valid @RequestBody SerieRequestDTO request) {
        SerieResponseDTO novaSerie = serieService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSerie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SerieResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody SerieRequestDTO request) {
        return ResponseEntity.ok(serieService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        serieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}