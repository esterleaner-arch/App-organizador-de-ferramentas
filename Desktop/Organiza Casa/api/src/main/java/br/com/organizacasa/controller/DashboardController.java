package br.com.organizacasa.controller;

import br.com.organizacasa.dto.DashboardResponse;
import br.com.organizacasa.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard")
public class DashboardController {

    private final DashboardService service;

    @GetMapping
    @Operation(summary = "Dashboard do sistema")
    public ResponseEntity<DashboardResponse> dashboard(){

        return ResponseEntity.ok(service.dashboard());

    }

}