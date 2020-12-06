package br.com.zup.estrelas.sme.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Gestao;

@Repository
public interface GestaoRepository extends CrudRepository<Gestao, Long> {

}
