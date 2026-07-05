package br.com.organizacasa.controller;

import br.com.organizacasa.dto.LocalRequest;
import br.com.organizacasa.dto.LocalResponse;
import br.com.organizacasa.service.LocalArmazenamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locais")
@RequiredArgsConstructor
@Tag(name = "Locais")
public class LocalArmazenamentoController {

    private final LocalArmazenamentoService service;

    @GetMapping
    @Operation(summary = "Listar locais")
    public ResponseEntity<List<LocalResponse>> listar(){

        return ResponseEntity.ok(service.listarTodos());

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar local")
    public ResponseEntity<LocalResponse> buscar(@PathVariable Long id){

        return ResponseEntity.ok(service.buscar(id));

    }

    @PostMapping
    @Operation(summary = "Cadastrar local")
    public ResponseEntity<LocalResponse> salvar(@Valid @RequestBody LocalRequest dto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.salvar(dto));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar local")
    public ResponseEntity<LocalResponse> atualizar(@PathVariable Long id,
                                                   @Valid @RequestBody LocalRequest dto){

        return ResponseEntity.ok(service.atualizar(id,dto));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir local")
    public ResponseEntity<Void> excluir(@PathVariable Long id){

        service.excluir(id);

        return ResponseEntity.noContent().build();

    }

}