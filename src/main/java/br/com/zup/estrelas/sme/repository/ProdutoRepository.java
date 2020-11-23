package br.com.zup.estrelas.sme.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    List<Produto> findByNomeLike(String nome);
}
