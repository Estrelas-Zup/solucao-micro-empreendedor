package br.com.zup.estrelas.sme.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Estoque;

@Repository
public interface EstoqueRepository extends CrudRepository<Estoque, Long> {
    List<Estoque> findAllByProdutoIdProduto(Long idProduto);
    
    Estoque findFirstByProdutoIdProduto(Long idProduto);
    
    List<Estoque> findAllByDisponibilidadeAndProdutoIdProdutoOrderByDataValidadeAsc(boolean disponibilidade, Long idProduto);

    @Query("SELECT SUM(e.quantidade) FROM Estoque e WHERE e.produto.idProduto = :idProduto AND e.perda = 1")
    Integer findEstoqueByIdProdutoEstoque(@Param("idProduto")  Long idProdutoEstoque);
}
