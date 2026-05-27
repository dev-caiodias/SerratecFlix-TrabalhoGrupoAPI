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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import org.springframework.validation.annotation.Validated;

@Validated
@RestController
@RequestMapping("/avaliacoes-series")
public class AvaliacaoSerieController {

    @Autowired
    private AvaliacaoSerieService service;

    @PostMapping
    @Operation(summary = "Cria uma nova avaliação de série", 
               description = "Salva uma nova avaliação de série no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Avaliação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos no corpo da requisição")
    })
    public ResponseEntity<AvaliacaoSerie> salvar(@RequestBody AvaliacaoSerie avaliacao) {
        AvaliacaoSerie nova = service.salvar(avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @GetMapping
    @Operation(summary = "Lista todas as avaliações de série", 
               description = "Retorna uma lista de todas as avaliações de série salvas no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliações encontradas"),
        @ApiResponse(responseCode = "204", description = "Nenhuma avaliação encontrada")
    })
    public ResponseEntity<List<AvaliacaoSerie>> listar() {
        List<AvaliacaoSerie> avaliacoes = service.listar();
        if (avaliacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma avaliação da série por ID", 
               description = "Retorna os detalhes da avaliação da série correspondentes ao ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação encontrada"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    })
    public ResponseEntity<AvaliacaoSerie> buscar(@PathVariable Long id) {
        AvaliacaoSerie avaliacao = service.buscarPorId(id);
        if (avaliacao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(avaliacao);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma avaliação da série por ID", 
               description = "Remove a avaliação da série correspondente ao ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Avaliação deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/acima-de/{notaMinima}")
    @Operation(summary = "Lista avaliações de séries com nota maior ou igual a N",
               description = "Retorna avaliações cuja nota seja maior ou igual ao valor informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliações encontradas"),
        @ApiResponse(responseCode = "204", description = "Nenhuma avaliação encontrada"),
        @ApiResponse(responseCode = "400", description = "Nota está fora do intervalo permitido (0-10)")
    })
    public ResponseEntity<List<AvaliacaoSerie>> listarAvaliacoesAcimaDe(
            @PathVariable Double notaMinima) {

        List<AvaliacaoSerie> avaliacoes = service.buscarPorNotaMinima(notaMinima);
        if (avaliacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(avaliacoes);
    }
}