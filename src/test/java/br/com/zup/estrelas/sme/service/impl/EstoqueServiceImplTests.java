package br.com.zup.estrelas.sme.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.ProdutoRepository;

@RunWith(MockitoJUnitRunner.class)
public class EstoqueServiceImplTests {

    @Mock
    EstoqueRepository estoqueRepository;

    @Mock
    ProdutoRepository produtoRepository;

    @InjectMocks
    EstoqueServiceImpl estoqueService;

    @Test
    public void deveAdicionarEstoqueComSucesso() {

    }

    @Test
    public void naoDeveAdicionarEstoqueComProdutoInexistente() {

    }

    @Test
    public void deveAlterarEstoqueComSucesso() {

    }

    @Test
    public void naoDeveAlterarEstoqueInexistente() {

    }

    @Test
    public void naoDeveAlterarEstoqueComProdutoInexistente() {

    }

    @Test
    public void deveContabilizarPerdaNoEstoqueComSucesso() {

    }

    @Test
    public void naoDeveContabilizarPerdaEmEstoqueInexistente() {

    }

    @Test
    public void naoDeveContabilizarPerdaEmEstoqueComPerdaComtabilizada() {

    }
}
