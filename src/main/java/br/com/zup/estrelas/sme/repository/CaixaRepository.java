package br.com.zup.estrelas.sme.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Caixa;

@Repository
public interface CaixaRepository extends CrudRepository<Caixa, Long> {

    List<Caixa> findByData(LocalDate data);

}
