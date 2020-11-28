package br.com.zup.estrelas.sme.repository;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Caixa;

@Repository
public interface CaixaRepository extends CrudRepository<Caixa, Long> {
    Optional<Caixa> findByData(LocalDate data);
}
