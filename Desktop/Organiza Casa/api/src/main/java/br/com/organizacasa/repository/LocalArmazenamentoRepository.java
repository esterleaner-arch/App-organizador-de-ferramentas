package br.com.organizacasa.repository;

import br.com.organizacasa.entity.LocalArmazenamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalArmazenamentoRepository extends JpaRepository<LocalArmazenamento, Long> {
}