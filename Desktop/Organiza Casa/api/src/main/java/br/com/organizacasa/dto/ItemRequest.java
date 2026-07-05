package br.com.organizacasa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemRequest {

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    private String descricao;

    private String foto;

    @Min(value = 0)
    private Integer quantidade;

    @Min(value = 0)
    private Integer quantidadeMinima;

    private String unidade;

    private String codigo;

    @NotNull(message = "Categoria obrigatória")
    private Long categoriaId;

    @NotNull(message = "Local obrigatório")
    private Long localId;

    @NotNull(message = "Usuário obrigatório")
    private Long usuarioId;

}