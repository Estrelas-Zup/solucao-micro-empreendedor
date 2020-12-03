package br.com.zup.estrelas.sme.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.RelatorioVenda;

@Repository
public interface RelatorioVendaRepository extends CrudRepository<RelatorioVenda, Long> {
    List<RelatorioVenda> findAllByVendaIdVenda(Long idVenda);

    List<RelatorioVenda> findAllByEstoqueProdutoIdProduto(Long idProduto);

    List<RelatorioVenda> findAllByVendaDataVendaBetween(LocalDate dataInicial, LocalDate dataAtual);

    List<RelatorioVenda> findAllByVendaDataVenda(LocalDate data);
}
