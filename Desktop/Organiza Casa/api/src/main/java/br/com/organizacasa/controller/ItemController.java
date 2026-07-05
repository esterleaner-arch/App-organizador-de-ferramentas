package br.com.organizacasa.controller;

import br.com.organizacasa.dto.ItemRequest;
import br.com.organizacasa.dto.ItemResponse;
import br.com.organizacasa.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
@RequiredArgsConstructor
@Tag(name = "Itens")
public class ItemController {

    private final ItemService service;

    @GetMapping
    @Operation(summary = "Listar todos os itens")
    public ResponseEntity<List<ItemResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar item por ID")
    public ResponseEntity<ItemResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Pesquisar item por nome")
    public ResponseEntity<List<ItemResponse>> buscarNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @GetMapping("/estoque-baixo")
    @Operation(summary = "Listar itens com estoque baixo")
    public ResponseEntity<List<ItemResponse>> estoqueBaixo() {
        return ResponseEntity.ok(service.estoqueBaixo());
    }

    @PostMapping
    @Operation(summary = "Cadastrar item")
    public ResponseEntity<ItemResponse> salvar(@Valid @RequestBody ItemRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar item")
    public ResponseEntity<ItemResponse> atualizar(@PathVariable Long id,
    @Valid @RequestBody ItemRequest dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir item")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/codigo")
public ResponseEntity<List<ItemResponse>> codigo(
        @RequestParam String codigo){

    return ResponseEntity.ok(service.buscarPorCodigo(codigo));

}

@GetMapping("/categoria")
public ResponseEntity<List<ItemResponse>> categoria(
        @RequestParam String categoria){

    return ResponseEntity.ok(service.buscarPorCategoria(categoria));

}

@GetMapping("/local")
public ResponseEntity<List<ItemResponse>> local(
        @RequestParam String local){

    return ResponseEntity.ok(service.buscarPorLocal(local));

}
}