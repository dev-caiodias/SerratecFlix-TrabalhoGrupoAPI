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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Categoria", description = "Cadastro de categorias para filmes e séries.")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@Operation(summary = "Lista de categorias", description = "Acessa a lista de categorias no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista acessada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não ha permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping
	public ResponseEntity<List<CategoriaDTOResponse>> listarTodas() {
		List<CategoriaDTOResponse> lista = categoriaService.listarTodas();
		return ResponseEntity.ok(lista);
	}

	@Operation(summary = "Busca de categoria por id", description = "Realiza a busca de uma categoria, através do seu ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não ha permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTOResponse> buscarPorId(@PathVariable Long id) {
		CategoriaDTOResponse response = categoriaService.buscaPorId(id);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Inserir uma categoria", description = "Inserir uma nova categoria no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoria registrada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não ha permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@PostMapping
	public ResponseEntity<CategoriaDTOResponse> criar(@Valid @RequestBody CategoriaDTORequest dto) {
		CategoriaDTOResponse response = categoriaService.criar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Operation(summary = "Atualizar cadastro de uma categoria", description = "Atualiza o cadastro de uma categoria no sistema através do seu ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não ha permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTOResponse> atualizar(@Valid @PathVariable Long id,
			@RequestBody CategoriaDTORequest dto) {
		CategoriaDTOResponse response = categoriaService.atualizar(id, dto);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Deleta uma categoria", description = "Deleta uma categoria no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoria excluida com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não ha permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		categoriaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
