package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.InformacoesRelatorioVendaDTO;
import br.com.zup.estrelas.sme.dto.RelatorioVendaDTO;
import br.com.zup.estrelas.sme.dto.RelatorioVendaDataDTO;
import br.com.zup.estrelas.sme.entity.RelatorioVenda;
import br.com.zup.estrelas.sme.repository.RelatorioVendaRepository;
import br.com.zup.estrelas.sme.service.RelatorioVendaService;

@Service
public class RelatorioVendaImpl implements RelatorioVendaService {

    @Autowired
    RelatorioVendaRepository relatorioVendaRepository;

    Double somaValorTotal = 0D;

    public RelatorioVendaDTO consultarRelatorioProduto(Long idProduto) {

        RelatorioVendaDTO relatorioVenda = new RelatorioVendaDTO();

        List<RelatorioVenda> relatorioVendaConsultada =
                (List<RelatorioVenda>) relatorioVendaRepository
                        .findAllByEstoqueProdutoIdProduto(idProduto);

        List<InformacoesRelatorioVendaDTO> informacoesRelatorio =
                new ArrayList<InformacoesRelatorioVendaDTO>();

        for (RelatorioVenda relatorio : relatorioVendaConsultada) {
            InformacoesRelatorioVendaDTO informacoes = new InformacoesRelatorioVendaDTO();
            informacoes.setIdVenda(relatorio.getVenda().getIdVenda());
            informacoes.setQuantidade(relatorio.getQuantidade());

            Double somaValorVendaProduto =
                    relatorio.getQuantidade() * relatorio.getEstoque().getProduto().getValorVenda();
            informacoes.setValorVenda(somaValorVendaProduto);

            somaValorTotal += somaValorVendaProduto;

            informacoesRelatorio.add(informacoes);

            relatorioVenda.setNomeProduto(relatorio.getEstoque().getProduto().getNome());
            relatorioVenda.setValorUnitario(relatorio.getEstoque().getProduto().getValorVenda());
        }

        relatorioVenda.setInformacoesRelario(informacoesRelatorio);
        relatorioVenda.setValorTotal(somaValorTotal);

        return relatorioVenda;
    }

    public RelatorioVendaDataDTO listarRelatorioVendaMes(LocalDate dataInicial) {

        RelatorioVendaDataDTO relatorioVendaDataDTO = new RelatorioVendaDataDTO();

        List<RelatorioVenda> vendasConsultadas = this.relatorioVendaRepository
                .findAllByVendaDataVendaBetween(dataInicial, LocalDate.now());

        List<RelatorioVendaDataDTO> relatorioVenda = new ArrayList<RelatorioVendaDataDTO>();

        for (RelatorioVenda vendaEntity : vendasConsultadas) {
            relatorioVendaDataDTO.setIdVenda(vendaEntity.getVenda().getIdVenda());
            relatorioVendaDataDTO.setNomeProduto(vendaEntity.getEstoque().getProduto().getNome());
            relatorioVendaDataDTO.setQuantidade(vendaEntity.getEstoque().getQuantidade());
            relatorioVendaDataDTO.setData(vendaEntity.getVenda().getDataVenda());
            relatorioVendaDataDTO
                    .setValorUnitario(vendaEntity.getEstoque().getProduto().getValorVenda());

            Double somaValor = vendaEntity.getQuantidade()
                    * vendaEntity.getEstoque().getProduto().getValorVenda();
            relatorioVendaDataDTO.setValorVenda(somaValor);

            somaValorTotal += somaValor;

            relatorioVendaDataDTO.setValorTotal(somaValorTotal);

            relatorioVenda.add(relatorioVendaDataDTO);
        }

        return relatorioVendaDataDTO;
    }
}
