package br.com.zup.estrelas.sme.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Caixa;

@Repository
public interface CaixaRepository extends CrudRepository<Caixa, Long> {
    Optional<Caixa> findByData(LocalDate data);

    boolean existsByData(LocalDate data);

    @Query("SELECT AVG((c.saldoInicial + c.valorTotal) - c.valorTotalDespesa) FROM Caixa c")
    Double averageOfMediaLucroDiario();

    @Query("SELECT AVG(c.valorTotalDespesa) FROM Caixa c")
    Double averageOfMediaDespesa();

    List<Caixa> findAllByDataBetween(LocalDate dataInicio, LocalDate dataFim);
}
