package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.ProdutoDTO;
import br.com.zup.estrelas.sme.entity.Produto;

public interface IProdutoService {

    public MensagemDTO adicionarProduto(ProdutoDTO produto);

    public List<Produto> listarProdutos();

    public Produto consultarPorId(Long idProduto);

    public MensagemDTO alterarProduto(Long idProduto, ProdutoDTO produto);

    public MensagemDTO removerProduto(Long idProduto);
}
