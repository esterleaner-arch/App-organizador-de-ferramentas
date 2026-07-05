package br.com.organizacasa.service;

import br.com.organizacasa.dto.UsuarioRequest;
import br.com.organizacasa.dto.UsuarioResponse;
import br.com.organizacasa.entity.Usuario;
import br.com.organizacasa.exception.BusinessException;
import br.com.organizacasa.exception.ResourceNotFoundException;
import br.com.organizacasa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado."));
    }

    public Usuario salvar(UsuarioRequest dto) {

        repository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    throw new BusinessException("E-mail já cadastrado.");
                });

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .telefone(dto.getTelefone())
                .build();

        return repository.save(usuario);
    }

    public Usuario atualizar(Long id, UsuarioRequest dto) {

        Usuario usuario = buscarPorId(id);

        usuario.setNome(dto.getNome());
        usuario.setTelefone(dto.getTelefone());

        return repository.save(usuario);

    }

    public void excluir(Long id) {

        Usuario usuario = buscarPorId(id);

        repository.delete(usuario);

    }

    public UsuarioResponse converter(Usuario usuario) {

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .telefone(usuario.getTelefone())
                .dataCadastro(usuario.getDataCadastro())
                .build();

    }

}