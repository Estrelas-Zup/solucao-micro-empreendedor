package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.ProdutoDTO;
import br.com.zup.estrelas.sme.entity.Produto;
import br.com.zup.estrelas.sme.exceptions.GenericException;

public interface ProdutoService {

    public MensagemDTO adicionarProduto(ProdutoDTO produto);

    public List<Produto> listarProdutos();

    public Produto consultarPorId(Long idProduto) throws GenericException;

    public List<Produto> consultarPeloNome(String nome);

    public MensagemDTO alterarProduto(Long idProduto, ProdutoDTO produto);

    public MensagemDTO removerProduto(Long idProduto);

}
