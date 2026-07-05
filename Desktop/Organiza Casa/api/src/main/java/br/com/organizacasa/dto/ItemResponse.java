package br.com.organizacasa.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ItemResponse {

    private Long id;

    private String nome;

    private String descricao;

    private String foto;

    private Integer quantidade;

    private Integer quantidadeMinima;

    private String unidade;

    private String codigo;

    private String categoria;

    private String local;

    private String usuario;

    private LocalDateTime dataCadastro;

    private Boolean estoqueBaixo;

}