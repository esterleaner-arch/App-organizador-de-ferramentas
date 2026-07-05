package br.com.organizacasa.service;

import br.com.organizacasa.dto.ItemRequest;
import br.com.organizacasa.dto.ItemResponse;
import br.com.organizacasa.entity.Categoria;
import br.com.organizacasa.entity.Item;
import br.com.organizacasa.entity.LocalArmazenamento;
import br.com.organizacasa.entity.Usuario;
import br.com.organizacasa.exception.ResourceNotFoundException;
import br.com.organizacasa.repository.CategoriaRepository;
import br.com.organizacasa.repository.ItemRepository;
import br.com.organizacasa.repository.LocalArmazenamentoRepository;
import br.com.organizacasa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalArmazenamentoRepository localRepository;
    private final HistoricoMovimentacaoService historicoService;

    public List<ItemResponse> listarTodos() {

        return itemRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();

    }

    public ItemResponse buscarPorId(Long id) {

        return converter(buscarEntidade(id));

    }

    public List<ItemResponse> buscarPorNome(String nome) {

        return itemRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::converter)
                .toList();

    }

    public List<ItemResponse> estoqueBaixo() {

        return itemRepository.findAll()
                .stream()
                .filter(item ->
                        item.getQuantidade() <= item.getQuantidadeMinima())
                .map(this::converter)
                .toList();

    }

    public ItemResponse salvar(ItemRequest dto) {

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada."));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado."));

        LocalArmazenamento local = localRepository.findById(dto.getLocalId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Local não encontrado."));

        Item item = Item.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .foto(dto.getFoto())
                .quantidade(dto.getQuantidade())
                .quantidadeMinima(dto.getQuantidadeMinima())
                .unidade(dto.getUnidade())
                .codigo(dto.getCodigo())
                .categoria(categoria)
                .usuario(usuario)
                .local(local)
                .build();

        Item itemSalvo = itemRepository.save(item);

        historicoService.registrar(
                itemSalvo,
                0,
                itemSalvo.getQuantidade(),
                "CADASTRO",
                "Cadastro inicial do item."
        );

        return converter(itemSalvo);

    }

    public ItemResponse atualizar(Long id, ItemRequest dto) {

        Item item = buscarEntidade(id);

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada."));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado."));

        LocalArmazenamento local = localRepository.findById(dto.getLocalId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Local não encontrado."));

        Integer quantidadeAnterior = item.getQuantidade();

        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());
        item.setFoto(dto.getFoto());
        item.setQuantidade(dto.getQuantidade());
        item.setQuantidadeMinima(dto.getQuantidadeMinima());
        item.setUnidade(dto.getUnidade());
        item.setCodigo(dto.getCodigo());

        item.setCategoria(categoria);
        item.setUsuario(usuario);
        item.setLocal(local);

        Item itemAtualizado = itemRepository.save(item);

        if (!Objects.equals(quantidadeAnterior, dto.getQuantidade())) {

            historicoService.registrar(
                    itemAtualizado,
                    quantidadeAnterior,
                    dto.getQuantidade(),
                    "ATUALIZACAO",
                    "Quantidade alterada pelo usuário."
            );

        }

        return converter(itemAtualizado);

    }

    public void excluir(Long id) {

        itemRepository.delete(buscarEntidade(id));

    }

    private Item buscarEntidade(Long id) {

        return itemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item não encontrado."));

    }

    private ItemResponse converter(Item item) {

        return ItemResponse.builder()
                .id(item.getId())
                .nome(item.getNome())
                .descricao(item.getDescricao())
                .foto(item.getFoto())
                .quantidade(item.getQuantidade())
                .quantidadeMinima(item.getQuantidadeMinima())
                .unidade(item.getUnidade())
                .codigo(item.getCodigo())
                .categoria(item.getCategoria().getNome())
                .usuario(item.getUsuario().getNome())
                .local(item.getLocal().getNome())
                .dataCadastro(item.getDataCadastro())
                .estoqueBaixo(item.getQuantidade() <= item.getQuantidadeMinima())
                .build();

    }

    public List<ItemResponse> buscarPorCodigo(String codigo){

    return itemRepository
            .findByCodigoContainingIgnoreCase(codigo)
            .stream()
            .map(this::converter)
            .toList();

}

public List<ItemResponse> buscarPorCategoria(String categoria){

    return itemRepository
            .findByCategoriaNomeContainingIgnoreCase(categoria)
            .stream()
            .map(this::converter)
            .toList();

}

public List<ItemResponse> buscarPorLocal(String local){

    return itemRepository
            .findByLocalNomeContainingIgnoreCase(local)
            .stream()
            .map(this::converter)
            .toList();

}

}