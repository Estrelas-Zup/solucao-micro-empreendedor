package br.com.zup.estrelas.sme.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Despesa;

@Repository
public interface DespesaRepository extends CrudRepository<Despesa, Long> {

}
