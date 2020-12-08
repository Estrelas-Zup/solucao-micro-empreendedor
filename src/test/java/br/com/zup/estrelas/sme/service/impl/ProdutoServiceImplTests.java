package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.ProdutoDTO;
import br.com.zup.estrelas.sme.entity.Estoque;
import br.com.zup.estrelas.sme.entity.Produto;
import br.com.zup.estrelas.sme.enums.UnidadeMedida;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.ProdutoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceImplTests {

    private static final String PRODUTO_CADASTRADO_COM_SUCESSO = "Produto cadastrado com sucesso!";
    private static final String PRODUTO_ALTERADO_COM_SUCESSO = "Produto alterado com sucesso!";
    private static final String PRODUTO_INEXISTENTE =
            "Infelizmente não foi possivel realizar a operação, produto inexistente.";
    private static final String PRODUTO_REMOVIDO_COM_SUCESSO = "Produto removido com sucesso!";
    private static final String PRODUTO_CADASTRADO_EM_ESTOQUE =
            "Infelizmente não foi possivel realizar a operação, produto cadastrado em estoque.";
    
    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    EstoqueRepository estoqueRepository;

    @InjectMocks
    ProdutoServiceImpl produtoService;

    @Test
    public void deveAdicionarUmProdutoComSucesso() {
        ProdutoDTO produto = gerarProduto();

        MensagemDTO mensagemRetornada = this.produtoService.adicionarProduto(produto);
        MensagemDTO mensagemEsperada = new MensagemDTO(PRODUTO_CADASTRADO_COM_SUCESSO);

        Assert.assertEquals("Deve criar um produto com sucesso", mensagemEsperada,
                mensagemRetornada);

    }

    @Test
    public void deveAlterarUmProdutoComSucesso() {
        ProdutoDTO produtoDTO = gerarProduto();
        Produto produto = new Produto();
        produto.setIdProduto(10L);
        BeanUtils.copyProperties(produtoDTO, produto);

        Optional<Produto> produtoBD = Optional.of(produto);

        Mockito.when(produtoRepository.findById(10L)).thenReturn(produtoBD);

        ProdutoDTO produtoAlterado = gerarProduto();
        produtoAlterado.setValorCusto(7D);
        produtoAlterado.setValorVenda(9.5D);

        MensagemDTO mensagemRetornada = this.produtoService.alterarProduto(10L, produtoAlterado);
        MensagemDTO mensagemEsperada = new MensagemDTO(PRODUTO_ALTERADO_COM_SUCESSO);

        Assert.assertEquals("Deve alterar um produto com sucesso", mensagemEsperada,
                mensagemRetornada);

    }

    @Test
    public void naoDeveAlterarUmProdutoInexistente() {
        ProdutoDTO produtoDTO = gerarProduto();

        Optional<Produto> produtoBD = Optional.empty();

        Mockito.when(produtoRepository.findById(10L)).thenReturn(produtoBD);

        MensagemDTO mensagemRetornada = this.produtoService.alterarProduto(10L, produtoDTO);
        MensagemDTO mensagemEsperada = new MensagemDTO(PRODUTO_INEXISTENTE);

        Assert.assertEquals("Não deve alterar um produto inexistente", mensagemEsperada,
                mensagemRetornada);

    }

    @Test
    public void deveRemoverProdutoComSucesso() {
        List<Estoque> estoqueConsultado = new ArrayList<Estoque>();
        Mockito.when(estoqueRepository.findAllByProdutoIdProduto(11L))
                .thenReturn(estoqueConsultado);
        Mockito.when(produtoRepository.existsById(11L)).thenReturn(true);

        MensagemDTO mensagemRetornada = this.produtoService.removerProduto(11L);
        MensagemDTO mensagemEsperada = new MensagemDTO(PRODUTO_REMOVIDO_COM_SUCESSO);

        Assert.assertEquals("Deve remover um produto com sucesso", mensagemEsperada,
                mensagemRetornada);

    }

    @Test
    public void naoDeveRemoverProdutoInexistente() {
        List<Estoque> estoqueConsultado = new ArrayList<Estoque>();
        Mockito.when(estoqueRepository.findAllByProdutoIdProduto(11L))
                .thenReturn(estoqueConsultado);
        Mockito.when(produtoRepository.existsById(11L)).thenReturn(false);

        MensagemDTO mensagemRetornada = this.produtoService.removerProduto(11L);
        MensagemDTO mensagemEsperada = new MensagemDTO(PRODUTO_INEXISTENTE);

        Assert.assertEquals("Não deve remover um produto inexistente", mensagemEsperada,
                mensagemRetornada);
    }

    @Test
    public void naoDeveRemoverProdutoCadastradoEmEstoque() {
        Produto produto = new Produto();
        produto.setIdProduto(11L);
        BeanUtils.copyProperties(gerarProduto(), produto);
        
        List<Estoque> estoqueConsultado = new ArrayList<Estoque>();
        Estoque estoque = new Estoque();
        estoque.setDataValidade(LocalDate.now());
        estoque.setDisponibilidade(true);
        estoque.setIdEstoque(1L);
        estoque.setPerda(false);
        estoque.setProduto(produto);
        estoque.setQuantidade(100);
        
        estoqueConsultado.add(estoque);
        
        Mockito.when(estoqueRepository.findAllByProdutoIdProduto(11L))
                .thenReturn(estoqueConsultado);
        Mockito.when(produtoRepository.existsById(11L)).thenReturn(true);

        MensagemDTO mensagemRetornada = this.produtoService.removerProduto(11L);
        MensagemDTO mensagemEsperada = new MensagemDTO(PRODUTO_CADASTRADO_EM_ESTOQUE);

        Assert.assertEquals("Não deve remover um produto cadastrado em estoque", mensagemEsperada,
                mensagemRetornada);
    }

    private ProdutoDTO gerarProduto() {
        ProdutoDTO produto = new ProdutoDTO();
        produto.setDescricao("Pão integral com cereais");
        produto.setMargemDesconto(1.2);
        produto.setNome("Pão integral");
        produto.setUnidadeMedida(UnidadeMedida.UNIDADE);
        produto.setValorCusto(2.0);
        produto.setValorVenda(4.5);
        return produto;
    }
}
