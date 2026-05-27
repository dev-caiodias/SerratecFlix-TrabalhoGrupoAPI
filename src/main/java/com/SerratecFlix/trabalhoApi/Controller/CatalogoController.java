package com.SerratecFlix.trabalhoApi.Controller;

import com.SerratecFlix.trabalhoApi.Dto.Request.CatalogoFiltroRequest;
import com.SerratecFlix.trabalhoApi.Dto.Response.CatalogoItemResponse;
import com.SerratecFlix.trabalhoApi.Service.CatalogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/catalogo")
@RequiredArgsConstructor
@Tag(name = "Catalogo", description = "Descoberta de filmes e séries com filtros")
public class CatalogoController {

    private final CatalogoService catalogoService;

    @GetMapping("/descobrir")
    @Operation(summary = "Filtra catálogo por categoria, tipo e nota mínima")
    public ResponseEntity<Page<CatalogoItemResponse>> descobrirGet(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) Double notaMinima,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(catalogoService.descobrir(categoria, tipo, notaMinima, pageable));
    }

    @PostMapping("/descobrir")
    @Operation(summary = "Filtra catálogo via body JSON")
    public ResponseEntity<Page<CatalogoItemResponse>> descobrirPost(
            @RequestBody CatalogoFiltroRequest filtro) {
        Pageable pageable = PageRequest.of(filtro.getPage(), filtro.getSize());
        return ResponseEntity.ok(catalogoService.descobrir(
                filtro.getCategoria(), filtro.getTipo(), filtro.getNotaMinima(), pageable));
    }

    @DeleteMapping("/preferencias/{id}")
    @Operation(summary = "Remove preferência salva do usuário")
    public ResponseEntity<Void> deletarPreferencia(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
