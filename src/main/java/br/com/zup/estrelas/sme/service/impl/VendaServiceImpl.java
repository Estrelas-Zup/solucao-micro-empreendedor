package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.AdicionarVendaDTO;
import br.com.zup.estrelas.sme.entity.Estoque;
import br.com.zup.estrelas.sme.entity.Venda;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.VendaRepository;
import br.com.zup.estrelas.sme.service.VendaService;

@Service
public class VendaServiceImpl implements VendaService {

    private static final String PRODUTO_INEXISTENTE_VENDA =
            "Infelizmente não foi possivel registrar a venda, produto inexistente.";

    private static final String VENDA_CADASTRADA_COM_SUCESSO = "Venda cadastrada com sucesso!";

    @Autowired
    VendaRepository repository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Override
    public MensagemDTO adicionarVenda(AdicionarVendaDTO adicionarVendaDTO) {
        Double valorTotalProdutos = 0D;
        List<Long> produtosVenda = adicionarVendaDTO.getIdProdutos();
        List<Estoque> estoques = new ArrayList<>();

        for (Long idProdutos : produtosVenda) {
            Optional<Estoque> estoqueOptional =
                    estoqueRepository.findByProdutoIdProduto(idProdutos.longValue());

            if (estoqueOptional.isEmpty()) {
                return new MensagemDTO(PRODUTO_INEXISTENTE_VENDA);
            }

            Estoque estoque = estoqueOptional.get();

            Double valorTotalProduto =
                    estoque.getQuantidade() * estoque.getProduto().getValorVenda();

            valorTotalProdutos += valorTotalProduto;
            estoques.add(estoque);
        }

        Venda venda = new Venda();
        BeanUtils.copyProperties(adicionarVendaDTO, venda);

        venda.setValorTotal(valorTotalProdutos);
        venda.setDataVenda(LocalDate.now());
        venda.setEstoques(estoques);
        
        repository.save(venda);
        return new MensagemDTO(VENDA_CADASTRADA_COM_SUCESSO);
    }

    @Override
    public MensagemDTO alterarVenda(Long numeroVenda, AdicionarVendaDTO vendaDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Venda buscarVendaPorId(Long idVenda) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Venda> listarVendas() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MensagemDTO removerVenda(Long numeroPedido) {
        // TODO Auto-generated method stub
        return null;
    }


}
