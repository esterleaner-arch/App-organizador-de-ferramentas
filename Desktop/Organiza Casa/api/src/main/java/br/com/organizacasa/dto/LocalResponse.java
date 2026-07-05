package br.com.organizacasa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalResponse {

    private Long id;

    private String nome;

    private String descricao;

    private Integer quantidadeItens;

}