package br.com.organizacasa.service;

import br.com.organizacasa.entity.HistoricoMovimentacao;
import br.com.organizacasa.entity.Item;
import br.com.organizacasa.exception.ResourceNotFoundException;
import br.com.organizacasa.repository.HistoricoMovimentacaoRepository;
import br.com.organizacasa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoricoMovimentacaoService {

    private final HistoricoMovimentacaoRepository repository;
    private final ItemRepository itemRepository;

    public List<HistoricoMovimentacao> listarTodos() {
        return repository.findAll();
    }

    public List<HistoricoMovimentacao> listarPorItem(Long itemId) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item não encontrado."));

        return repository.findAll()
                .stream()
                .filter(h -> h.getItem().getId().equals(item.getId()))
                .toList();

    }

    public void registrar(Item item,
                          Integer quantidadeAnterior,
                          Integer quantidadeNova,
                          String tipo,
                          String observacao){

        HistoricoMovimentacao historico = HistoricoMovimentacao.builder()
                .item(item)
                .tipoMovimento(tipo)
                .quantidadeAnterior(quantidadeAnterior)
                .quantidadeNova(quantidadeNova)
                .observacao(observacao)
                .dataMovimento(LocalDateTime.now())
                .build();

        repository.save(historico);

    }



    
}