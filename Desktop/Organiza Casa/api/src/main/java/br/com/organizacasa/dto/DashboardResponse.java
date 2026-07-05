package br.com.organizacasa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private Long totalItens;

    private Long totalCategorias;

    private Long totalLocais;

    private Long estoqueBaixo;

}