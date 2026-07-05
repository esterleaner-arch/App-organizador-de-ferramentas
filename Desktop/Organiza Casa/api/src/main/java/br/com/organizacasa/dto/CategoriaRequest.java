package br.com.organizacasa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String icone;

    private String cor;
}