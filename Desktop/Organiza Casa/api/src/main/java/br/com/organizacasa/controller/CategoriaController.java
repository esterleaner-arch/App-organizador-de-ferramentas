package br.com.organizacasa.controller;

import br.com.organizacasa.dto.CategoriaRequest;
import br.com.organizacasa.entity.Categoria;
import br.com.organizacasa.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorias")
public class CategoriaController {

    private final CategoriaService service;

    @GetMapping
    @Operation(summary = "Listar categorias")
    public ResponseEntity<List<Categoria>> listar(){

        return ResponseEntity.ok(service.listarTodos());

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria")
    public ResponseEntity<Categoria> buscar(@PathVariable Long id){

        return ResponseEntity.ok(service.buscarPorId(id));

    }

    @PostMapping
    @Operation(summary = "Cadastrar categoria")
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody CategoriaRequest dto){

        return ResponseEntity.ok(service.salvar(dto));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id,
                                               @Valid @RequestBody CategoriaRequest dto){

        return ResponseEntity.ok(service.atualizar(id,dto));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir categoria")
    public ResponseEntity<Void> excluir(@PathVariable Long id){

        service.excluir(id);

        return ResponseEntity.noContent().build();

    }

}