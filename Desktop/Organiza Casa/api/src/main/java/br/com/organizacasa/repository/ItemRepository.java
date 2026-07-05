package br.com.organizacasa.repository;

import br.com.organizacasa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNomeContainingIgnoreCase(String nome);

    List<Item> findByCodigoContainingIgnoreCase(String codigo);

    List<Item> findByCategoriaNomeContainingIgnoreCase(String categoria);

    List<Item> findByLocalNomeContainingIgnoreCase(String local);

}