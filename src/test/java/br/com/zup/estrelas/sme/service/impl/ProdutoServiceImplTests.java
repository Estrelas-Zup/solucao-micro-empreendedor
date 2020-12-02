package br.com.zup.estrelas.sme.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.ProdutoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceImplTests {

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    EstoqueRepository estoqueRepository;

    @InjectMocks
    ProdutoServiceImpl produtoService;

    @Test
    public void deveAdicionarUmProdutoComSucesso() {

    }

    @Test
    public void deveAlterarUmProdutoComSucesso() {

    }

    @Test
    public void naoDeveAlterarUmProdutoInexistente() {

    }

    @Test
    public void deveRemoverProdutoComSucesso() {

    }

    @Test
    public void naoDeveRemoverProdutoInexistente() {

    }

    @Test
    public void naoDeveRemoverProdutoCadastradoEmEstoque() {

    }
}
