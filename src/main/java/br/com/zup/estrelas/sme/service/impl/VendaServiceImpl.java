package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.AdicionarVendaDTO;
import br.com.zup.estrelas.sme.dto.AlterarVendaDTO;
import br.com.zup.estrelas.sme.dto.ControleEstoqueVendaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.ProdutosVendaDTO;
import br.com.zup.estrelas.sme.entity.Caixa;
import br.com.zup.estrelas.sme.entity.Estoque;
import br.com.zup.estrelas.sme.entity.RelatorioVenda;
import br.com.zup.estrelas.sme.entity.Venda;
import br.com.zup.estrelas.sme.exceptions.GenericException;
import br.com.zup.estrelas.sme.repository.CaixaRepository;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.RelatorioVendaRepository;
import br.com.zup.estrelas.sme.repository.VendaRepository;
import br.com.zup.estrelas.sme.service.VendaService;

@Service
public class VendaServiceImpl implements VendaService {

    private static final String DATA_DA_VENDA_INFERIOR_A_DATA_ATUAL =
            "Infelizmente não foi possivel relizar a operação, data da venda inferior a data atual!";
    private static final String VENDA_REMOVIDA_COM_SUCESSO = "Venda removida com sucesso!";
    private static final String DESCONTO_MAIOR_QUE_TOTAL_VENDA =
            "Infelizmente não foi possivel relizar a operação, valor do desconto não pode ser maior que o valor total da venda!";
    private static final String VALOR_DESCONTO_SUPERIOR_AO_VALOR_TOTAL =
            "Infelizmente não foi possivel realizar a operação, valor desconto superior ao valor total.";
    private static final String VENDA_ALTERADA_COM_SUCESSO = "Venda alterada com sucesso!";
    private static final String VENDA_INEXISTENTE =
            "Infelizmente não foi possivel realizar a operação, venda inexistente.";
    private static final String VENDA_CADASTRADA_COM_SUCESSO = "Venda cadastrada com sucesso!";
    private static final String ABERTURA_DE_CAIXA_NAO_REALIZADA =
            "Infelizmente não foi possivel realizar a operação, ainda não houve abertura de caixa no dia de hoje.";


    @Autowired
    VendaRepository repository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    RelatorioVendaRepository relatorioVendaRepository;

    @Autowired
    CaixaRepository caixaRepository;

    public MensagemDTO adicionarVenda(AdicionarVendaDTO adicionarVendaDTO) {
        Double valorTotalProdutos = 0D;

        List<ProdutosVendaDTO> produtosVenda = adicionarVendaDTO.getProdutosVenda();

        Optional<Caixa> caixaConsultado = caixaRepository.findByData(LocalDate.now());

        if (caixaConsultado.isEmpty()) {
            return new MensagemDTO(ABERTURA_DE_CAIXA_NAO_REALIZADA);
        }

        Caixa caixa = caixaConsultado.get();

        for (ProdutosVendaDTO produtosVendaDTO : produtosVenda) {
            List<Estoque> estoqueConsultado = consultaEstoque(produtosVendaDTO);

            if (estoqueConsultado.isEmpty()) {
                return new MensagemDTO(
                        "Infelizmente não foi possivel relizar a operação, não temos o produto com id: "
                                + produtosVendaDTO.getIdProduto() + " disponivel em estoque.");
            }

            int quantidadeDesejada = produtosVendaDTO.getQuantidade();
            int quantidadeTotalEstoque = 0;
            Double precoUnitararioProduto = 0D;
            long idProdutoConsultado = 0;

            for (Estoque estoque : estoqueConsultado) {
                quantidadeTotalEstoque += estoque.getQuantidade();
                idProdutoConsultado = estoque.getProduto().getIdProduto();
                precoUnitararioProduto = estoque.getProduto().getValorVenda();
            }

            boolean quantidadeDesejadaMaiorQueEstoque = quantidadeTotalEstoque < quantidadeDesejada;

            if (quantidadeDesejadaMaiorQueEstoque) {
                return new MensagemDTO(String.format(
                        "A quantidade em estoque do produto com id: %d, inferior a quantidade desejada, temos apenas %d",
                        idProdutoConsultado, quantidadeTotalEstoque));
            }

            Double valorVendaProduto = produtosVendaDTO.getQuantidade() * precoUnitararioProduto;

            valorTotalProdutos += valorVendaProduto;
        }

        boolean valorDescontoMaiorQueValorTotal =
                adicionarVendaDTO.getValorDesconto() > valorTotalProdutos;

        if (valorDescontoMaiorQueValorTotal) {
            return new MensagemDTO(DESCONTO_MAIOR_QUE_TOTAL_VENDA);
        }

        Double valorTotalComDesconto = valorTotalProdutos - adicionarVendaDTO.getValorDesconto();

        List<ControleEstoqueVendaDTO> listaEstoqueASerAdicionadoRelatorio = new ArrayList<>();

        for (ProdutosVendaDTO produtosVendaDTO : produtosVenda) {
            List<Estoque> estoqueConsultado = consultaEstoque(produtosVendaDTO);

            int quantidadeDesejada = produtosVendaDTO.getQuantidade();

            listaEstoqueASerAdicionadoRelatorio
                    .addAll(alteraQuantidadeDoEstoque(estoqueConsultado, quantidadeDesejada));
        }

        Venda venda = new Venda();
        BeanUtils.copyProperties(adicionarVendaDTO, venda);

        caixa.setValorTotal(caixa.getValorTotal() + valorTotalComDesconto);
        caixaRepository.save(caixa);

        venda.setValorTotal(valorTotalComDesconto);
        venda.setDataVenda(LocalDate.now());
        venda.setCaixa(caixa);

        repository.save(venda);

        adicionarRelatorioVenda(venda, listaEstoqueASerAdicionadoRelatorio);
        return new MensagemDTO(VENDA_CADASTRADA_COM_SUCESSO);
    }

    public MensagemDTO alterarVenda(Long idVenda, AlterarVendaDTO alterarVendaDTO) {
        Optional<Venda> vendaConsultada = repository.findById(idVenda);

        if (vendaConsultada.isEmpty()) {
            return new MensagemDTO(VENDA_INEXISTENTE);
        }

        Venda venda = vendaConsultada.get();

        boolean vericaDataVendaIgualDataAtual = venda.getDataVenda().equals(LocalDate.now());

        if (!vericaDataVendaIgualDataAtual) {
            return new MensagemDTO(DATA_DA_VENDA_INFERIOR_A_DATA_ATUAL);
        }

        boolean verificarValorDescontoAtualIgualNovoValor =
                venda.getValorDesconto().equals(alterarVendaDTO.getValorDesconto());

        if (!verificarValorDescontoAtualIgualNovoValor) {
            Double valorTotalSemDesconto = venda.getValorTotal() + venda.getValorDesconto();

            boolean verificarValorDescontoSuperiorValorTotal =
                    alterarVendaDTO.getValorDesconto() > valorTotalSemDesconto;

            if (verificarValorDescontoSuperiorValorTotal) {
                return new MensagemDTO(VALOR_DESCONTO_SUPERIOR_AO_VALOR_TOTAL);
            }

            Double descontoAlterado = alterarVendaDTO.getValorDesconto();

            Double novoValorTotal = valorTotalSemDesconto - descontoAlterado;

            Double diferencaDescontoParaCaixa = venda.getValorTotal() - novoValorTotal;

            venda.setValorTotal(novoValorTotal);
            venda.setValorDesconto(descontoAlterado);

            Caixa caixa = caixaRepository.findByData(LocalDate.now()).get();
            Double novoSaltoTotalCaixa = caixa.getValorTotal() - diferencaDescontoParaCaixa;
            caixa.setValorTotal(novoSaltoTotalCaixa);
            caixaRepository.save(caixa);
        }

        venda.setObservacao(alterarVendaDTO.getObservacao());

        repository.save(venda);

        return new MensagemDTO(VENDA_ALTERADA_COM_SUCESSO);
    }

    public Venda buscarVendaPorId(Long idVenda) throws GenericException {
        return repository.findById(idVenda)
                .orElseThrow(() -> new GenericException(VENDA_INEXISTENTE));
    }

    public List<Venda> listarVendas() {
        return (List<Venda>) repository.findAll();
    }

    public MensagemDTO removerVenda(Long idVenda) {
        if (repository.existsById(idVenda)) {

            List<RelatorioVenda> relatorioVendas =
                    relatorioVendaRepository.findAllByVendaIdVenda(idVenda);

            Venda venda = repository.findById(idVenda).get();

            double valorVendaRemovida = venda.getValorTotal();

            Caixa caixa = caixaRepository.findByData(LocalDate.now()).get();
            double novoSaldoCaixa = caixa.getValorTotal() - valorVendaRemovida;
            caixa.setValorTotal(novoSaldoCaixa);
            caixaRepository.save(caixa);

            adionaProdutosAoEstoque(relatorioVendas);

            relatorioVendaRepository.deleteAll(relatorioVendas);

            repository.deleteById(idVenda);

            return new MensagemDTO(VENDA_REMOVIDA_COM_SUCESSO);
        }
        return new MensagemDTO(VENDA_INEXISTENTE);
    }

    private void adionaProdutosAoEstoque(List<RelatorioVenda> relatorioVendas) {
        for (RelatorioVenda relatorio : relatorioVendas) {
            Estoque estoque =
                    estoqueRepository.findById(relatorio.getEstoque().getIdEstoque()).get();

            estoque.setDisponibilidade(true);
            int novaQuantidadeEstoque = estoque.getQuantidade() + relatorio.getQuantidade();
            estoque.setQuantidade(novaQuantidadeEstoque);
            estoqueRepository.save(estoque);
        }
    }

    public void adicionarRelatorioVenda(Venda venda,
            List<ControleEstoqueVendaDTO> listaEstoqueASerAdicionadoRelatorio) {

        for (ControleEstoqueVendaDTO controleEstoqueVendaDTO : listaEstoqueASerAdicionadoRelatorio) {
            relatorioVendaRepository
                    .save(montarObjetoRelatorioVenda(controleEstoqueVendaDTO, venda));
        }
    }

    private List<ControleEstoqueVendaDTO> alteraQuantidadeDoEstoque(List<Estoque> estoqueConsultado,
            int quantidadeDesejada) {

        List<ControleEstoqueVendaDTO> listaEstoqueASerAdicionadoRelatorio = new ArrayList<>();

        for (Estoque estoque : estoqueConsultado) {
            int quantidade = 0;
            if (quantidadeDesejada >= estoque.getQuantidade()) {
                quantidadeDesejada = quantidadeDesejada - estoque.getQuantidade();

                quantidade = estoque.getQuantidade();

                listaEstoqueASerAdicionadoRelatorio
                        .add(montarObjetoControleEstoqueVendaDTO(estoque, quantidade));

                estoque.setQuantidade(0);
                estoque.setDisponibilidade(false);
                estoqueRepository.save(estoque);
            } else {
                quantidade = quantidadeDesejada;
                listaEstoqueASerAdicionadoRelatorio
                        .add(montarObjetoControleEstoqueVendaDTO(estoque, quantidade));

                estoque.setQuantidade(estoque.getQuantidade() - quantidadeDesejada);
                quantidadeDesejada = 0;
                estoqueRepository.save(estoque);

                return listaEstoqueASerAdicionadoRelatorio;
            }

        }
        return listaEstoqueASerAdicionadoRelatorio;
    }

    private List<Estoque> consultaEstoque(ProdutosVendaDTO produtosVendaDTO) {
        List<Estoque> estoqueConsultado =
                estoqueRepository.findAllByDisponibilidadeAndProdutoIdProdutoOrderByDataValidadeAsc(
                        true, produtosVendaDTO.getIdProduto());
        return estoqueConsultado;
    }

    private ControleEstoqueVendaDTO montarObjetoControleEstoqueVendaDTO(Estoque estoque,
            int quantidade) {
        ControleEstoqueVendaDTO controleEstoqueVendaDTO = new ControleEstoqueVendaDTO();

        controleEstoqueVendaDTO.setEstoque(estoque);
        controleEstoqueVendaDTO.setQuantidade(quantidade);

        return controleEstoqueVendaDTO;
    }

    private RelatorioVenda montarObjetoRelatorioVenda(
            ControleEstoqueVendaDTO controleEstoqueVendaDTO, Venda venda) {
        RelatorioVenda relatorioVenda = new RelatorioVenda();

        Long idProdutoEstoque = controleEstoqueVendaDTO.getEstoque().getProduto().getIdProduto();

        relatorioVenda.setQuantidade(controleEstoqueVendaDTO.getQuantidade());
        relatorioVenda.setVenda(venda);
        relatorioVenda.setEstoque(controleEstoqueVendaDTO.getEstoque());
        relatorioVenda.setIdProdutoEstoque(idProdutoEstoque);

        return relatorioVenda;
    }

}
