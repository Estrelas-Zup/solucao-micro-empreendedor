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

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public MensagemDTO adicionarEstoque(EstoqueDTO estoqueDTO) {

        Estoque estoque = new Estoque();

        Optional<Produto> produtoConsultado = produtoRepository.findById(estoqueDTO.getIdProduto());

        Produto produto = produtoConsultado.get();

        BeanUtils.copyProperties(estoqueDTO, estoque);
        estoque.setProduto(produto);
        estoqueRepository.save(estoque);

        return new MensagemDTO("Estoque criado com sucesso");
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
        List<Estoque> estoquesPoduto = estoqueRepository.findAllByProdutoIdProduto(idProduto);
        return estoquesPoduto;
    }

    public List<Estoque> listarEstoques() {
        List<Estoque> estoques = (List<Estoque>) estoqueRepository.findAll();
        return estoques;
    }
}
