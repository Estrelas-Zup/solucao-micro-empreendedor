package br.com.zup.estrelas.sme.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.ProdutoDTO;
import br.com.zup.estrelas.sme.entity.Produto;
import br.com.zup.estrelas.sme.service.impl.IProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    IProdutoService produtoService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO adicionaProduto(@RequestBody ProdutoDTO produto) {
        return produtoService.adicionarProduto(produto);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Produto> listaProduto() {
        return produtoService.listarProdutos();
    }

    @GetMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Produto consultarPorId(@PathVariable Long idProduto) {
        return produtoService.consultarPorId(idProduto);
    }

    @PutMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alteraProduto(@PathVariable Long idProduto, @RequestBody ProdutoDTO produto) {
        return produtoService.alterarProduto(idProduto, produto);
    }

    @DeleteMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO removeProduto(@PathVariable Long idProduto) {
        return produtoService.removerProduto(idProduto);
    }
}
