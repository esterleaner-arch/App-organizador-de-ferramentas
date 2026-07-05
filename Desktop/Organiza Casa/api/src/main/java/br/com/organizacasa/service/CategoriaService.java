package br.com.organizacasa.service;

import br.com.organizacasa.dto.CategoriaRequest;
import br.com.organizacasa.entity.Categoria;
import br.com.organizacasa.exception.ResourceNotFoundException;
import br.com.organizacasa.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public List<Categoria> listarTodos() {

        return repository.findAll();

    }

    public Categoria buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada."));

    }

    public Categoria salvar(CategoriaRequest dto) {

        Categoria categoria = Categoria.builder()
                .nome(dto.getNome())
                .icone(dto.getIcone())
                .cor(dto.getCor())
                .build();

        return repository.save(categoria);

    }

    public Categoria atualizar(Long id, CategoriaRequest dto) {

        Categoria categoria = buscarPorId(id);

        categoria.setNome(dto.getNome());
        categoria.setIcone(dto.getIcone());
        categoria.setCor(dto.getCor());

        return repository.save(categoria);

    }

    public void excluir(Long id) {

        repository.delete(buscarPorId(id));

    }

}