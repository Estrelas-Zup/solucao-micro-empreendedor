package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.EstoqueDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Estoque;
import br.com.zup.estrelas.sme.entity.Produto;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.ProdutoRepository;
import br.com.zup.estrelas.sme.service.EstoqueService;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    private static final String ESTOQUE_CRIADO_COM_SUCESSO = "Estoque criado com sucesso!";

    private static final String PRODUTO_INEXISTENTE = "Infelizmente não foi possivel realizar a operação, produto inexistente.";

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public MensagemDTO adicionarEstoque(EstoqueDTO estoqueDTO) {
        Estoque estoque = new Estoque();

        Optional<Produto> produtoConsultado = produtoRepository.findById(estoqueDTO.getIdProduto());
        
        if(produtoConsultado.isEmpty()) {
            return new MensagemDTO(PRODUTO_INEXISTENTE);
        }
        
        Produto produto = produtoConsultado.get();

        BeanUtils.copyProperties(estoqueDTO, estoque);
        estoque.setProduto(produto);
        estoqueRepository.save(estoque);

        return new MensagemDTO(ESTOQUE_CRIADO_COM_SUCESSO);
    }

    public MensagemDTO alterarEstoque(Long idEstorque, EstoqueDTO estoqueDTO) {
        Optional<Estoque> estoqueConsultado = estoqueRepository.findById(idEstorque);

        Estoque estoque = estoqueConsultado.get();

        Optional<Produto> produtoConsultado = produtoRepository.findById(estoqueDTO.getIdProduto());

        Produto produto = produtoConsultado.get();

        BeanUtils.copyProperties(estoqueDTO, estoque);
        estoque.setProduto(produto);
        estoqueRepository.save(estoque);

        return new MensagemDTO("Estoque alterado com sucesso");
    }

    public List<Estoque> consultarEstoquePorProduto(Long idProduto) {
        return estoqueRepository.findAllByProdutoIdProduto(idProduto);
    }

    public List<Estoque> listarEstoques() {
        return (List<Estoque>) estoqueRepository.findAll();
    }
}
