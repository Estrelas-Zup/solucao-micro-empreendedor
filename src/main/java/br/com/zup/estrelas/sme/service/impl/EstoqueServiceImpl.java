package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.ContabilizaPerdaDTO;
import br.com.zup.estrelas.sme.dto.EstoqueDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Estoque;
import br.com.zup.estrelas.sme.entity.Produto;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.ProdutoRepository;
import br.com.zup.estrelas.sme.service.EstoqueService;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    private static final String ESTOQUE_ALTERADO_COM_SUCESSO = "Estoque alterado com sucesso";
    private static final String ESTOQUE_JÁ_CONTABILIZADO_COMO_PERDA =
            "Estoque já foi contabilizado como perda";
    private static final String PERDA_ADICIONADA_COM_SUCESSO = "Perda adicionada com sucesso";
    private static final String ESTOQUE_INEXISTENTE =
            "Não foi possivel realizar a operação, estoque inexistente";
    private static final String ESTOQUE_CRIADO_COM_SUCESSO = "Estoque criado com sucesso!";
    private static final String PRODUTO_INEXISTENTE =
            "Infelizmente não foi possivel realizar a operação, produto inexistente.";

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public MensagemDTO adicionarEstoque(Long idProduto, EstoqueDTO estoqueDTO) {
        Estoque estoque = new Estoque();

        Optional<Produto> produtoConsultado = produtoRepository.findById(idProduto);

        if (produtoConsultado.isEmpty()) {
            return new MensagemDTO(PRODUTO_INEXISTENTE);
        }

        Produto produto = produtoConsultado.get();

        BeanUtils.copyProperties(estoqueDTO, estoque);
        estoque.setProduto(produto);
        estoqueRepository.save(estoque);

        return new MensagemDTO(ESTOQUE_CRIADO_COM_SUCESSO);
    }

    public MensagemDTO alterarEstoque(Long idProduto, Long idEstoque, EstoqueDTO estoqueDTO) {
        Optional<Estoque> estoqueConsultado = estoqueRepository.findById(idEstoque);

        if (estoqueConsultado.isEmpty()) {
            return new MensagemDTO(ESTOQUE_INEXISTENTE);
        }

        Estoque estoque = estoqueConsultado.get();

        Optional<Produto> produtoConsultado = produtoRepository.findById(idProduto);

        if (produtoConsultado.isEmpty()) {
            return new MensagemDTO(PRODUTO_INEXISTENTE);
        }

        Produto produto = produtoConsultado.get();

        BeanUtils.copyProperties(estoqueDTO, estoque);
        estoque.setProduto(produto);
        estoqueRepository.save(estoque);

        return new MensagemDTO(ESTOQUE_ALTERADO_COM_SUCESSO);
    }

    public List<Estoque> consultarEstoquePorProduto(Long idProduto) {
        return estoqueRepository.findAllByProdutoIdProduto(idProduto);
    }

    public List<Estoque> listarEstoques() {
        return (List<Estoque>) estoqueRepository.findAll();
    }

    public MensagemDTO contablizarPerda(Long idProduto, Long idEstoque,
            ContabilizaPerdaDTO contabilizaPerdaDTO) {

        Optional<Produto> produtoConsultado = produtoRepository.findById(idProduto);

        if (produtoConsultado.isEmpty()) {
            return new MensagemDTO(PRODUTO_INEXISTENTE);
        }

        Optional<Estoque> estoqueConsultado = estoqueRepository.findById(idEstoque);

        if (estoqueConsultado.isEmpty()) {
            return new MensagemDTO(ESTOQUE_INEXISTENTE);
        }

        Estoque estoque = estoqueConsultado.get();

        if (estoque.isPerda()) {
            return new MensagemDTO(ESTOQUE_JÁ_CONTABILIZADO_COMO_PERDA);
        }

        // TODO: Desenvolver metodo para monstar estrutura
        estoque.setMotivoPerda(contabilizaPerdaDTO.getMotivoPerda());
        estoque.setPerda(true);
        estoque.setDisponibilidade(false);
        estoqueRepository.save(estoque);

        return new MensagemDTO(PERDA_ADICIONADA_COM_SUCESSO);
    }
}
