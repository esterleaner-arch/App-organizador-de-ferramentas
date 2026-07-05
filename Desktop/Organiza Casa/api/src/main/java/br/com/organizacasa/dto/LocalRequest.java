package br.com.organizacasa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LocalRequest {

    @NotBlank(message = "Nome do local é obrigatório")
    private String nome;

    private String descricao;
}