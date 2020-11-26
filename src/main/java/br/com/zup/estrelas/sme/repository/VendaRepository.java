package br.com.zup.estrelas.sme.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Venda;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Long> {
    Venda findFirstByOrderByIdVendaDesc();
}
