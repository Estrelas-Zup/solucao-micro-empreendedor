package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.ProdutoDTO;
import br.com.zup.estrelas.sme.entity.Produto;
import br.com.zup.estrelas.sme.repository.ProdutoRepository;
import br.com.zup.estrelas.sme.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private static final String PRODUTO_CADASTRADO_COM_SUCESSO = "Produto cadastrado com sucesso!";
    private static final String PRODUTO_INEXISTENTE =
            "Infelizmente não foi possivel realizar a operação, produto inexistente.";
    private static final String PRODUTO_REMOVIDO_COM_SUCESSO = "Produto removido com sucesso!";
    private static final String PRODUTO_ALTERADO_COM_SUCESSO = "Produto alterado com sucesso!";

    @Autowired
    ProdutoRepository produtoRepository;

    public MensagemDTO adicionarProduto(ProdutoDTO produtoDTO) {

        Produto produto = new Produto();

        BeanUtils.copyProperties(produtoDTO, produto);

        produtoRepository.save(produto);
        return new MensagemDTO(PRODUTO_CADASTRADO_COM_SUCESSO);
    }

    public List<Produto> listarProdutos() {
        return (List<Produto>) produtoRepository.findAll();
    }

    public Produto consultarPorId(Long idProduto) {
        return produtoRepository.findById(idProduto).orElse(null);
    }

    public List<Produto> consultarPeloNome(String nome) {
        return produtoRepository.findByNomeStartingWith(nome);
    }

    public MensagemDTO alterarProduto(Long idProduto, ProdutoDTO produtoDTO) {

        Optional<Produto> produtoConsultado = produtoRepository.findById(idProduto);

        if (produtoConsultado.isEmpty()) {
            return new MensagemDTO(PRODUTO_INEXISTENTE);
        }

        Produto produtoAlterado = produtoConsultado.get();
        BeanUtils.copyProperties(produtoDTO, produtoAlterado);

        produtoRepository.save(produtoAlterado);

        return new MensagemDTO(PRODUTO_ALTERADO_COM_SUCESSO);
    }

    public MensagemDTO removerProduto(Long idProduto) {

        if (produtoRepository.existsById(idProduto)) {
            produtoRepository.deleteById(idProduto);
            return new MensagemDTO(PRODUTO_REMOVIDO_COM_SUCESSO);
        }

        return new MensagemDTO(PRODUTO_INEXISTENTE);
    }

}
