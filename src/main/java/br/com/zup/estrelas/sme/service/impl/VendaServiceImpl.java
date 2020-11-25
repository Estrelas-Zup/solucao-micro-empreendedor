package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
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
import br.com.zup.estrelas.sme.entity.Venda;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.VendaRepository;
import br.com.zup.estrelas.sme.service.VendaService;

@Service
public class VendaServiceImpl implements VendaService {

    private static final String VALOR_DESCONTO_SUPERIOR_AO_VALOR_TOTAL =
            "Infelizmente não foi possivel realizar a operação, valor desconto superior ao valor total.";

    private static final String VENDA_ALTERADA_COM_SUCESSO = "Venda alterada com sucesso!";

    private static final String VENDA_INEXISTENTE =
            "Infelizmente não foi possivel realizar a operação, venda inexistente.";

    private static final String PRODUTO_INEXISTENTE_VENDA =
            "Infelizmente não foi possivel  relizar a operação, produto inexistente.";

    private static final String VENDA_CADASTRADA_COM_SUCESSO = "Venda cadastrada com sucesso!";

    @Autowired
    VendaRepository repository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Override
    public MensagemDTO adicionarVenda(AdicionarVendaDTO adicionarVendaDTO) {
        Double valorTotalProdutos = 0D;

        List<ProdutosVendaDTO> produtosVenda = adicionarVendaDTO.getProdutosVenda();

        List<Estoque> estoques = new ArrayList<>();

        for (ProdutosVendaDTO produtosVendaDTO : produtosVenda) {
            Optional<Estoque> estoqueConsultado = estoqueRepository
                    .findFirstByDisponibilidadeAndProdutoIdProdutoOrderByDataValidadeAsc(true,
                            produtosVendaDTO.getIdProduto());

            // TODO: Validar quantidade em estoque
            if (estoqueConsultado.isEmpty()) {
                return new MensagemDTO(PRODUTO_INEXISTENTE_VENDA);
            }

            Estoque estoque = estoqueConsultado.get();

            Double valorVendaProduto =
                    produtosVendaDTO.getQuantidade() * estoque.getProduto().getValorVenda();

            valorTotalProdutos += valorVendaProduto;
            estoques.add(estoque);
        }


        Venda venda = new Venda();
        BeanUtils.copyProperties(adicionarVendaDTO, venda);
        Double valorTotalComDesconto = valorTotalProdutos - adicionarVendaDTO.getValorDesconto();
        // TODO: validar valor desconto menor que valorTotalProdutos
        venda.setValorTotal(valorTotalComDesconto);
        venda.setDataVenda(LocalDate.now());
        venda.setEstoques(estoques);

        repository.save(venda);
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

    @Override
    public Venda buscarVendaPorId(Long idVenda) {
        return repository.findById(idVenda).orElse(null);
    }

    @Override
    public List<Venda> listarVendas() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MensagemDTO removerVenda(Long idVenda) {
        // TODO Auto-generated method stub
        return null;
    }


}
