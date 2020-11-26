package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.ProdutosVendaDTO;
import br.com.zup.estrelas.sme.dto.AdicionarVendaDTO;
import br.com.zup.estrelas.sme.dto.AlterarVendaDTO;
import br.com.zup.estrelas.sme.entity.Estoque;
import br.com.zup.estrelas.sme.entity.RelatorioVenda;
import br.com.zup.estrelas.sme.entity.Venda;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.RelatorioVendaRepository;
import br.com.zup.estrelas.sme.repository.VendaRepository;
import br.com.zup.estrelas.sme.service.VendaService;

@Service
public class VendaServiceImpl implements VendaService {

    private static final String VALOR_DESCONTO_SUPERIOR_AO_VALOR_TOTAL =
            "Infelizmente não foi possivel realizar a operação, valor desconto superior ao valor total.";

    private static final String VENDA_ALTERADA_COM_SUCESSO = "Venda alterada com sucesso!";

    private static final String VENDA_INEXISTENTE =
            "Infelizmente não foi possivel realizar a operação, venda inexistente.";

    private static final String PRODUTO_INEXISTENTE =
            "Infelizmente não foi possivel relizar a operação, produto inexistente.";

    private static final String VENDA_CADASTRADA_COM_SUCESSO = "Venda cadastrada com sucesso!";

    @Autowired
    VendaRepository repository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    RelatorioVendaRepository relatorioVendaRepository;

    @Override
    public MensagemDTO adicionarVenda(AdicionarVendaDTO adicionarVendaDTO) {
        Double valorTotalProdutos = 0D;

        List<ProdutosVendaDTO> produtosVenda = adicionarVendaDTO.getProdutosVenda();
        
        // TODO: Validar idProduto iguais
        
        for (ProdutosVendaDTO produtosVendaDTO : produtosVenda) {
            Optional<Estoque> estoqueConsultado = estoqueRepository
                    .findFirstByDisponibilidadeAndProdutoIdProdutoOrderByDataValidadeAsc(true,
                            produtosVendaDTO.getIdProduto());

            // TODO: Validar quantidade em estoque
            if (estoqueConsultado.isEmpty()) {
                return new MensagemDTO(PRODUTO_INEXISTENTE);
            }

            Estoque estoque = estoqueConsultado.get();

            Double valorVendaProduto =
                    produtosVendaDTO.getQuantidade() * estoque.getProduto().getValorVenda();

            valorTotalProdutos += valorVendaProduto;
        }


        Venda venda = new Venda();
        BeanUtils.copyProperties(adicionarVendaDTO, venda);

        // TODO: validar valor desconto menor que valorTotalProdutos
        Double valorTotalComDesconto = valorTotalProdutos - adicionarVendaDTO.getValorDesconto();

        venda.setValorTotal(valorTotalComDesconto);
        venda.setDataVenda(LocalDate.now());

        repository.save(venda);

        adicionarRelatorioVenda(adicionarVendaDTO);
        return new MensagemDTO(VENDA_CADASTRADA_COM_SUCESSO);
    }

    @Override
    public MensagemDTO alterarVenda(Long idVenda, AlterarVendaDTO alterarVendaDTO) {
        Optional<Venda> vendaConsultada = repository.findById(idVenda);

        if (vendaConsultada.isEmpty()) {
            return new MensagemDTO(VENDA_INEXISTENTE);
        }

        Venda venda = vendaConsultada.get();

        boolean verificarValorDescontoAtualIgualNovoValor =
                venda.getValorDesconto().equals(alterarVendaDTO.getValorDesconto());

        if (!verificarValorDescontoAtualIgualNovoValor) {
            Double valorTotalSemDesconto = venda.getValorTotal() + venda.getValorDesconto();

            boolean verificarValorDescontoInferiorOuIgualValorTotal =
                    alterarVendaDTO.getValorDesconto() <= valorTotalSemDesconto;

            if (!verificarValorDescontoInferiorOuIgualValorTotal) {
                return new MensagemDTO(VALOR_DESCONTO_SUPERIOR_AO_VALOR_TOTAL);
            }

            Double descontoAlterado = alterarVendaDTO.getValorDesconto();

            Double novoValorTotal = valorTotalSemDesconto - descontoAlterado;
            venda.setValorTotal(novoValorTotal);
            venda.setValorDesconto(descontoAlterado);
        }

        venda.setObservacao(alterarVendaDTO.getObservacao());

        repository.save(venda);

        return new MensagemDTO(VENDA_ALTERADA_COM_SUCESSO);
    }

    public Venda buscarVendaPorId(Long idVenda) {
        return repository.findById(idVenda).orElse(null);
    }

    public List<Venda> listarVendas() {
        return (List<Venda>) repository.findAll();
    }

    public MensagemDTO removerVenda(Long idVenda) {
        if(repository.existsById(idVenda)) {
            List<RelatorioVenda> relatorioVendas = relatorioVendaRepository.findAllByVendaIdVenda(idVenda);
            relatorioVendaRepository.deleteAll(relatorioVendas);
            
            repository.deleteById(idVenda);
            
            return new MensagemDTO("Venda removida com sucesso!");
        }
        return new MensagemDTO(VENDA_INEXISTENTE);
    }

    public void adicionarRelatorioVenda(AdicionarVendaDTO adicionarVendaDTO) {
        List<ProdutosVendaDTO> produtosVenda = adicionarVendaDTO.getProdutosVenda();

        Venda ultimaVendaAdicionada = repository.findFirstByOrderByIdVendaDesc();


        for (ProdutosVendaDTO produtosVendaDTO : produtosVenda) {
            RelatorioVenda relatorioVenda = new RelatorioVenda();

            Estoque estoque =
                    estoqueRepository.findFirstByProdutoIdProduto(produtosVendaDTO.getIdProduto());
            
            relatorioVenda.setQuantidade(produtosVendaDTO.getQuantidade());

            relatorioVenda.setVenda(ultimaVendaAdicionada);
            relatorioVenda.setEstoque(estoque);
            
            relatorioVendaRepository.save(relatorioVenda);
        }
    }
}
