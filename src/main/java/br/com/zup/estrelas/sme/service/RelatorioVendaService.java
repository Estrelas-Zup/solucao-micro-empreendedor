package br.com.zup.estrelas.sme.service;

import java.time.LocalDate;
import br.com.zup.estrelas.sme.dto.RelatorioVendaDTO;
import br.com.zup.estrelas.sme.dto.RelatorioVendaDataDTO;

public interface RelatorioVendaService {
    public RelatorioVendaDTO consultarRelatorioProduto(Long idProduto);

    public RelatorioVendaDataDTO listarRelatorioVendaMes(LocalDate dataInicio);
}
