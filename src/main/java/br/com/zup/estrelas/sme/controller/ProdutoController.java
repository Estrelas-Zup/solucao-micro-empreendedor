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
import br.com.zup.estrelas.sme.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/produtos")
@Api(value = "Produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Adiciona produto")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Criado com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public MensagemDTO adicionaProduto(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.adicionarProduto(produtoDTO);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Listar produtos")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Procura do produto feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum produto encontrado!")})
    public List<Produto> listaProduto() {
        return produtoService.listarProdutos();
    }

    @GetMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Consulta produto por Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Procura do Id do produto feita com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum produto encontrado pelo Id!")})
    public Produto consultarPorId(@PathVariable Long idProduto) {
        return produtoService.consultarPorId(idProduto);
    }

    @GetMapping(path = "/nomes/{nome}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Consulta produto pelo nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Procura do nome do produto feita com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum produto encontrado pelo nome!")})
    // TODO verificar possibilidade de passar nome no corpo ConsultarPeloNomeDTO
    public List<Produto> consultarPeloNome(@PathVariable String nome) {
        return produtoService.consultarPeloNome(nome);
    }

    @PutMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Altera produto")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Alteração do produto feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum produto alterado!")})
    public MensagemDTO alteraProduto(@PathVariable Long idProduto,
            @RequestBody ProdutoDTO produto) {
        return produtoService.alterarProduto(idProduto, produto);
    }

    @DeleteMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Exclui produto")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Remoção do produto feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum produto removido!")})
    public MensagemDTO removeProduto(@PathVariable Long idProduto) {
        return produtoService.removerProduto(idProduto);
    }
}
