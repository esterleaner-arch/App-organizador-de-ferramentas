package br.com.organizacasa.repository;

import br.com.organizacasa.entity.HistoricoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoMovimentacaoRepository extends JpaRepository<HistoricoMovimentacao, Long> {
}