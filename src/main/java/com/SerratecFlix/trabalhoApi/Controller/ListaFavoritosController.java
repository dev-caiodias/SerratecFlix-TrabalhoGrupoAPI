package com.SerratecFlix.trabalhoApi.Controller;


import com.SerratecFlix.trabalhoApi.Domain.ListaFavoritos;
import com.SerratecFlix.trabalhoApi.Dto.Request.ListaFavoritosDTORequest;
import com.SerratecFlix.trabalhoApi.Dto.Response.ListaFavoritosDTOResponse;
import com.SerratecFlix.trabalhoApi.Repository.ListaFavoritosRepository;
import com.SerratecFlix.trabalhoApi.Service.ListaFavoritosService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Retorna todas as listas")
    public ResponseEntity<List<ListaFavoritosDTOResponse>> listar() {
        return ResponseEntity.ok(listaFavoritosService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna a lista do usuario do Id")
    public ResponseEntity<ListaFavoritosDTOResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(listaFavoritosService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova lista para o usuario")
    public ResponseEntity<ListaFavoritosDTOResponse> criar(@Valid @RequestBody ListaFavoritosDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(listaFavoritosService.criar(request));
    }


    @PutMapping("/lista/{id}")
    @Operation(summary = "Editar ou adicionar na lista")
    public ResponseEntity<ListaFavoritosDTOResponse>atualizar(@PathVariable Long id, @RequestBody @Valid
                                                ListaFavoritosDTORequest request){
        return ResponseEntity.ok(listaFavoritosService.atualizar(request, id));
    }


    @DeleteMapping("/{id}") @Operation(summary = "Deleta uma lista") public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        listaFavoritosService.deletar(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/filmes/{filmeId}")
    @Operation(summary = "Adiciona filmes na lista")
    public ResponseEntity<ListaFavoritos> adicionaFilme(@PathVariable Long id, @PathVariable Long filmeId){
        return ResponseEntity.ok(listaFavoritosService.adicionaFilme(id, filmeId));
    }


    @PostMapping("/{id}/series/{serieId}")
    @Operation(summary = "Adiciona series na lista")
    public ResponseEntity<ListaFavoritos> adicionaSerie(@PathVariable Long id, @PathVariable Long serieId){
        return ResponseEntity.ok(listaFavoritosService.adicionaSerie(id, serieId));
    }
}
