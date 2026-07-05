package br.com.organizacasa.service;

import br.com.organizacasa.dto.LocalRequest;
import br.com.organizacasa.dto.LocalResponse;
import br.com.organizacasa.entity.LocalArmazenamento;
import br.com.organizacasa.exception.ResourceNotFoundException;
import br.com.organizacasa.repository.LocalArmazenamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalArmazenamentoService {

    private final LocalArmazenamentoRepository repository;

    public List<LocalResponse> listarTodos() {

        return repository.findAll()
                .stream()
                .map(this::converter)
                .toList();

    }

    public LocalResponse buscar(Long id){

        return converter(buscarEntidade(id));

    }

    public LocalResponse salvar(LocalRequest dto){

        LocalArmazenamento local = LocalArmazenamento.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();

        return converter(repository.save(local));

    }

    public LocalResponse atualizar(Long id, LocalRequest dto){

        LocalArmazenamento local = buscarEntidade(id);

        local.setNome(dto.getNome());
        local.setDescricao(dto.getDescricao());

        return converter(repository.save(local));

    }

    public void excluir(Long id){

        repository.delete(buscarEntidade(id));

    }

    private LocalArmazenamento buscarEntidade(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Local não encontrado."));

    }

    private LocalResponse converter(LocalArmazenamento local){

        return LocalResponse.builder()
                .id(local.getId())
                .nome(local.getNome())
                .descricao(local.getDescricao())
                .quantidadeItens(local.getItens() == null ? 0 : local.getItens().size())
                .build();

    }

}
