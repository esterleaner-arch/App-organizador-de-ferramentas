package br.com.organizacasa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaResponse {

    private Long id;

    private String nome;

    private String icone;

    private String cor;
}