package com.SerratecFlix.trabalhoApi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SerratecFlix.trabalhoApi.Dto.Request.AvaliacaoFilmeDTOResquest;
import com.SerratecFlix.trabalhoApi.Dto.Response.AvaliacaoFilmeDTOResponse;
import com.SerratecFlix.trabalhoApi.Service.AvaliacaoFilmeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Avaliação filme", description = "Cadastro de avaliações para filmes.")
@RestController
@RequestMapping("/avaliacoes-filme")
public class AvaliacaoFilmeController {

	@Autowired
	private AvaliacaoFilmeService avaliacaoFilmeService;

	@Operation(summary = "Lista de avaliações de filmes", description = "Acessa a lista de avaliações de filmes no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista acessada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não ha permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	/* GET listar todos */
	@GetMapping
	public ResponseEntity<List<AvaliacaoFilmeDTOResponse>> listarTodas() {
		List<AvaliacaoFilmeDTOResponse> lista = avaliacaoFilmeService.listarTodos();
		return ResponseEntity.ok(lista);
	}

	@Operation(summary = "Busca de avaliação por id", description = "Realiza a busca de uma avaliação de filme, através do seu ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Avaliação encontrada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não ha permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	/* GET por ID */
	@GetMapping("/{id}")
	public ResponseEntity<AvaliacaoFilmeDTOResponse> buscarPorId(@PathVariable Long id) {
		AvaliacaoFilmeDTOResponse response = avaliacaoFilmeService.buscarPorId(id);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Busca de categoria por id do filme", description = "Realiza a busca de uma avaliação, através do ID do filme")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Avaliações encontrada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não ha permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	/* GEt pelo id do filme */
	@GetMapping("/filme/{filmeId}")
	public ResponseEntity<List<AvaliacaoFilmeDTOResponse>> buscarPorFilme(@PathVariable Long filmeId) {
		List<AvaliacaoFilmeDTOResponse> lista = avaliacaoFilmeService.buscarPorFilme(filmeId);
		return ResponseEntity.ok(lista);
	}

	@Operation(summary = "Registrar uma avaliação", description = "Inserir uma nova avaliação no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Avaliação registrada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não ha permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	/* POST */
	@PostMapping
	public ResponseEntity<AvaliacaoFilmeDTOResponse> criar(@Valid @RequestBody AvaliacaoFilmeDTOResquest request) {
		AvaliacaoFilmeDTOResponse response = avaliacaoFilmeService.criar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Operation(summary = "Deleta uma avaliação", description = "Deleta uma avaliação no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Avaliação excluida com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não ha permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	/* DELETE */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		avaliacaoFilmeService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
