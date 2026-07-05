package br.com.organizacasa.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UsuarioResponse {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private LocalDateTime dataCadastro;
}