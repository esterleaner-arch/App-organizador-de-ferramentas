package br.com.organizacasa.controller;

import br.com.organizacasa.entity.HistoricoMovimentacao;
import br.com.organizacasa.service.HistoricoMovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historicos")
@RequiredArgsConstructor
@Tag(name = "Histórico")
public class HistoricoMovimentacaoController {

    private final HistoricoMovimentacaoService service;

    @GetMapping
    @Operation(summary = "Listar histórico")
    public ResponseEntity<List<HistoricoMovimentacao>> listar(){

        return ResponseEntity.ok(service.listarTodos());

    }

    @GetMapping("/item/{id}")
    @Operation(summary = "Histórico por Item")
    public ResponseEntity<List<HistoricoMovimentacao>> buscar(@PathVariable Long id){

        return ResponseEntity.ok(service.listarPorItem(id));

    }

}