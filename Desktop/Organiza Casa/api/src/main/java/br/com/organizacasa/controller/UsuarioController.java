package br.com.organizacasa.controller;

import br.com.organizacasa.dto.UsuarioRequest;
import br.com.organizacasa.dto.UsuarioResponse;
import br.com.organizacasa.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários")
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    @Operation(summary = "Listar usuários")
    public ResponseEntity<List<UsuarioResponse>> listar(){

        List<UsuarioResponse> lista = service.listarTodos()
                .stream()
                .map(service::converter)
                .toList();

        return ResponseEntity.ok(lista);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable Long id){

        return ResponseEntity.ok(
                service.converter(service.buscarPorId(id))
        );

    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário")
    public ResponseEntity<UsuarioResponse> salvar(@Valid @RequestBody UsuarioRequest dto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.converter(service.salvar(dto)));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id,
                                                     @Valid @RequestBody UsuarioRequest dto){

        return ResponseEntity.ok(
                service.converter(service.atualizar(id,dto))
        );

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário")
    public ResponseEntity<Void> excluir(@PathVariable Long id){

        service.excluir(id);

        return ResponseEntity.noContent().build();

    }

}