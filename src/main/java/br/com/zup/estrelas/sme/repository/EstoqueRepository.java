package br.com.zup.estrelas.sme.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Estoque;

@Repository
public interface EstoqueRepository extends CrudRepository<Estoque, Long> {
    List<Estoque> findAllByProdutoIdProduto(Long idProduto);
    Optional<Estoque> findByProdutoIdProduto(Long idProduto);
}
