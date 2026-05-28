package com.SerratecFlix.trabalhoApi.Controller;

import com.SerratecFlix.trabalhoApi.Domain.Filme;
import com.SerratecFlix.trabalhoApi.Dto.Request.CategoriaDTORequest;
import com.SerratecFlix.trabalhoApi.Dto.Request.UsuarioDTORequest;
import com.SerratecFlix.trabalhoApi.Dto.Response.FilmeResponse;
import com.SerratecFlix.trabalhoApi.Dto.Response.UsuarioDTOResponse;
import com.SerratecFlix.trabalhoApi.Service.RecomendacaoService;
import com.SerratecFlix.trabalhoApi.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RecomendacaoService recomendacaoService;

    @GetMapping
    @Operation(summary = "Lista todos os usuários")
    public ResponseEntity<List<UsuarioDTOResponse>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca usuário por ID")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    public ResponseEntity<UsuarioDTOResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria um novo usuário")
    @ApiResponse(responseCode = "409", description = "username / email já existem.")
    public ResponseEntity<UsuarioDTOResponse> criar(@Valid @RequestBody UsuarioDTORequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário existente")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    public ResponseEntity<UsuarioDTOResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTORequest request) {
        return ResponseEntity.ok(usuarioService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um usuário por ID")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    /*CRUD para recomendações de filme - parte do Vitor */
    @GetMapping("/{id}/recomendacoes")
    public ResponseEntity<List<FilmeResponse>> obterRecomendacoes(@PathVariable Long id, @RequestParam(required = false) Double notaMinima) {
        List<FilmeResponse> recomendacoes = recomendacaoService.obterRecomendacoes(id, notaMinima);
        return ResponseEntity.ok(recomendacoes);
    }

    /*POST */
    @PostMapping("/{id}/preferencias")
    public ResponseEntity<Void> salvarPreferencia(@PathVariable Long id, @RequestBody CategoriaDTORequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*DELETE */
    @DeleteMapping("/{id}/preferencias/{categoriaId}")
    public ResponseEntity<Void> deletarPreferencia(@PathVariable Long id, @PathVariable Long categoriaId) {
        recomendacaoService.removerPreferenciaUsuario(id, categoriaId);
        return ResponseEntity.noContent().build();
    }
}
