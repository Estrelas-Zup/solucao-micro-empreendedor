package br.com.zup.estrelas.sme.service;

import java.time.LocalDate;
import java.util.List;
import br.com.zup.estrelas.sme.dto.RelatorioVendaDTO;
import br.com.zup.estrelas.sme.dto.RelatorioVendaDataDTO;
import br.com.zup.estrelas.sme.entity.RelatorioVenda;

public interface RelatorioVendaService {
    public RelatorioVendaDTO consultarRelatorioProduto(Long idProduto);

    public RelatorioVendaDataDTO listarRelatorioVendaMes(LocalDate dataInicial,
            LocalDate dataFinal);

    public List<RelatorioVenda> consultarRelatorioVendaPorData(LocalDate data);
}
