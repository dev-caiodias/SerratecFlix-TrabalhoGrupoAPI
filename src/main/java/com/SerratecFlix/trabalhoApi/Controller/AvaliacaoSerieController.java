package com.SerratecFlix.trabalhoApi.Controller;

import java.util.List;

import com.SerratecFlix.trabalhoApi.Dto.Request.AvaliacaoSerieRequestDto;
import com.SerratecFlix.trabalhoApi.Dto.Response.AvaliacaoSerieResponseDto;
import com.SerratecFlix.trabalhoApi.Service.AvaliacaoSerieService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
@RestController
@RequestMapping("/avaliacoes-series")
@RequiredArgsConstructor
public class AvaliacaoSerieController {

    private final AvaliacaoSerieService service;

    @PostMapping
    @Operation(summary = "Cria uma nova avaliação de série")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Avaliação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<AvaliacaoSerieResponseDto> salvar(
            @RequestBody @Valid AvaliacaoSerieRequestDto dto) {

        AvaliacaoSerieResponseDto response = service.salvar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    @Operation(summary = "Lista todas as avaliações")
    public ResponseEntity<List<AvaliacaoSerieResponseDto>> listar() {

        List<AvaliacaoSerieResponseDto> lista = service.listar();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca avaliação por ID")
    public ResponseEntity<AvaliacaoSerieResponseDto> buscar(
            @PathVariable Long id) {

        AvaliacaoSerieResponseDto dto = service.buscarPorId(id);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma avaliação")
    public ResponseEntity<AvaliacaoSerieResponseDto> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AvaliacaoSerieRequestDto dto) {

        AvaliacaoSerieResponseDto response =
                service.atualizar(id, dto);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma avaliação")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        boolean deletou = service.deletar(id);

        if (!deletou) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/acima-de/{nota}")
    @Operation(summary = "Lista avaliações acima da nota")
    public ResponseEntity<List<AvaliacaoSerieResponseDto>>
            buscarPorNota(@PathVariable Double nota) {

        List<AvaliacaoSerieResponseDto> lista =
                service.buscarPorNotaMinima(nota);

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/media/{serieId}")
    @Operation(summary = "Calcula média das avaliações da série")
    public ResponseEntity<Double> calcularMedia(
            @PathVariable Long serieId) {

        Double media = service.calcularMediaPorSerie(serieId);

        return ResponseEntity.ok(media);
    }
}