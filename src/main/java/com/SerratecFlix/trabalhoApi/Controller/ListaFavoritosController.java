package com.SerratecFlix.trabalhoApi.Controller;


import com.SerratecFlix.trabalhoApi.Domain.ListaFavoritos;
import com.SerratecFlix.trabalhoApi.Dto.Request.ListaFavoritosDTORequest;
import com.SerratecFlix.trabalhoApi.Dto.Response.ListaFavoritosDTOResponse;
import com.SerratecFlix.trabalhoApi.Repository.ListaFavoritosRepository;
import com.SerratecFlix.trabalhoApi.Service.ListaFavoritosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas")
public class ListaFavoritosController {

    @Autowired
    private ListaFavoritosRepository listaFavoritosRepository;
    @Autowired
    private ListaFavoritosService listaFavoritosService;

    @GetMapping
    @Operation(summary = "Retorna todas as listas", description = "Retorna listas de filmes e séries favoritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listas retornadas com sucesso")
    })
    public ResponseEntity<List<ListaFavoritosDTOResponse>> listar() {
        return ResponseEntity.ok(listaFavoritosService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna a lista do usuario do Id", description = "Retorna uma lista de especificada por um Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista não encontrada")
    })
    public ResponseEntity<ListaFavoritosDTOResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(listaFavoritosService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova lista para o usuario", description = "Cadastra uma nova lista de favoritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista cadastrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<ListaFavoritosDTOResponse> criar(@Valid @RequestBody ListaFavoritosDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(listaFavoritosService.criar(request));
    }


    @PutMapping("/lista/{id}")
    @Operation(summary = "Editar ou adicionar na lista", description = "Atualiza os dados de uma lista de favoritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista atualizada!"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos"),
            @ApiResponse(responseCode = "404", description = "Lista não encontrada")
    })
    public ResponseEntity<ListaFavoritosDTOResponse>atualizar(@PathVariable Long id, @RequestBody @Valid
                                                ListaFavoritosDTORequest request){
        return ResponseEntity.ok(listaFavoritosService.atualizar(request, id));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma lista", description = "Remove uma lista do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        listaFavoritosService.deletar(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/filmes/{filmeId}")
    @Operation(summary = "Adiciona filmes na lista", description = "Adicione filmes na lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme adicionado")
    })
    public ResponseEntity<ListaFavoritos> adicionaFilme(@PathVariable Long id, @PathVariable Long filmeId){
        return ResponseEntity.ok(listaFavoritosService.adicionaFilme(id, filmeId));
    }


    @PostMapping("/{id}/series/{serieId}")
    @Operation(summary = "Adiciona series na lista", description = "Adiciona series na lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serie adicionada")
    })
    public ResponseEntity<ListaFavoritos> adicionaSerie(@PathVariable Long id, @PathVariable Long serieId){
        return ResponseEntity.ok(listaFavoritosService.adicionaSerie(id, serieId));
    }


    @PostMapping("/{id}/compartilhar")
    @Operation(summary = "Tornar lista pública", description = "Deixe sua lista publica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sua lista agora é publica")
    })
    public ResponseEntity<ListaFavoritosDTOResponse> compartilhar(@PathVariable Long id){
        return ResponseEntity.ok(listaFavoritosService.compartilhar(id));
    }


    @DeleteMapping("/{id}/compartilhar")
    @Operation(summary = "Privar lista", description = "Deixe sua lista privada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sua lista agora é privada")
    })
    public ResponseEntity<ListaFavoritosDTOResponse> descompartilhar(@PathVariable Long id) {
        return ResponseEntity.ok(listaFavoritosService.descompartilhar(id));
    }
}
