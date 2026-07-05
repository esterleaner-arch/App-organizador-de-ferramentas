package br.com.organizacasa.service;

import br.com.organizacasa.dto.DashboardResponse;
import br.com.organizacasa.repository.CategoriaRepository;
import br.com.organizacasa.repository.ItemRepository;
import br.com.organizacasa.repository.LocalArmazenamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ItemRepository itemRepository;

    private final CategoriaRepository categoriaRepository;

    private final LocalArmazenamentoRepository localRepository;

    public DashboardResponse dashboard(){

        Long estoqueBaixo = itemRepository.findAll()
                .stream()
                .filter(item ->
                        item.getQuantidade() <= item.getQuantidadeMinima())
                .count();

        return DashboardResponse.builder()

                .totalItens(itemRepository.count())

                .totalCategorias(categoriaRepository.count())

                .totalLocais(localRepository.count())

                .estoqueBaixo(estoqueBaixo)

                .build();

    }

}