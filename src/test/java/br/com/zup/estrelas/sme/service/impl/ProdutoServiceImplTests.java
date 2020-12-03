package br.com.zup.estrelas.sme.service.impl;

import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.ProdutoDTO;
import br.com.zup.estrelas.sme.entity.Produto;
import br.com.zup.estrelas.sme.enums.UnidadeMedida;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.ProdutoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceImplTests {

    private static final String PRODUTO_CADASTRADO_COM_SUCESSO = "Produto cadastrado com sucesso!";
    private static final String PRODUTO_ALTERADO_COM_SUCESSO = "Produto alterado com sucesso!";
    private static final String PRODUTO_REMOVIDO_COM_SUCESSO = "Produto removido ocm sucesso!";

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    EstoqueRepository estoqueRepository;

    @InjectMocks
    ProdutoServiceImpl produtoService;

    public ProdutoDTO informacoesDoProduto(ProdutoDTO produtoDTO) {
        produtoDTO.setNome("Pao");
        produtoDTO.setDescricao("Pão com manteiga");
        produtoDTO.setUnidadeMedida(UnidadeMedida.UNIDADE);
        produtoDTO.setValorVenda(3.50);
        produtoDTO.setValorCusto(2.50);
        produtoDTO.setMargemDesconto(0.50);

        return produtoDTO;
    }

    @Test
    public void deveAdicionarUmProdutoComSucesso() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        informacoesDoProduto(produtoDTO);

        Mockito.when(produtoRepository.findByNomeStartingWith(produtoDTO.getNome()));

        MensagemDTO mensagemRetornada = this.produtoService.adicionarProduto(produtoDTO);
        MensagemDTO mensagemEsperada = new MensagemDTO(PRODUTO_CADASTRADO_COM_SUCESSO);

        Assert.assertEquals("A mensagem retornada deve ser indicativo de sucesso", mensagemEsperada,
                mensagemRetornada);
    }

    @Test
    public void deveAlterarUmProdutoComSucesso() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        informacoesDoProduto(produtoDTO);

        Produto produto = new Produto();

        Optional<Produto> produtoBlindado = Optional.of(produto);

        Mockito.when(produtoRepository.findByNomeStartingWith("Pao"));

        MensagemDTO mensagemRetornada = this.produtoService.alterarProduto(1L, produtoDTO);
        MensagemDTO mensagemEsperada = new MensagemDTO(PRODUTO_ALTERADO_COM_SUCESSO);

        Assert.assertEquals("Envia uma mensagem caso o produto seja alterado com sucesso",
                mensagemEsperada, mensagemRetornada);
    }

    @Test
    public void naoDeveAlterarUmProdutoInexistente() {

    }

    @Test
    public void deveRemoverProdutoComSucesso() {

        Mockito.when(produtoRepository.existsById(1L)).thenReturn(true);

        MensagemDTO mensagemRetornada = this.produtoService.removerProduto(1L);
        MensagemDTO mensagemEsperada = new MensagemDTO(PRODUTO_REMOVIDO_COM_SUCESSO);

        Assert.assertEquals("Envia uma mensagem caso o produto seja removido com sucesso",
                mensagemEsperada, mensagemRetornada);
    }

    @Test
    public void naoDeveRemoverProdutoInexistente() {

    }

    @Test
    public void naoDeveRemoverProdutoCadastradoEmEstoque() {

    }
}
