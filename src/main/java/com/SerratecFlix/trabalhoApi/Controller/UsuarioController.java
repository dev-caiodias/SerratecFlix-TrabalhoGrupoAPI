package com.SerratecFlix.trabalhoApi.Controller;

import com.SerratecFlix.trabalhoApi.Dto.Request.UsuarioDTORequest;
import com.SerratecFlix.trabalhoApi.Dto.Response.UsuarioDTOResponse;
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
}
