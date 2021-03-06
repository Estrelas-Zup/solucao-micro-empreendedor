package br.com.zup.estrelas.sme.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.ProdutoDTO;
import br.com.zup.estrelas.sme.entity.Produto;
import br.com.zup.estrelas.sme.exceptions.GenericException;
import br.com.zup.estrelas.sme.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/produtos")
@Api(value = "Produto", description = "REST API Produto", tags = {"Produto"})
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Adicionar produto")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Criado com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
    @ResponseStatus(HttpStatus.CREATED)
    public MensagemDTO adicionaProduto(@Valid @RequestBody ProdutoDTO produtoDTO)
            throws GenericException {
        return produtoService.adicionarProduto(produtoDTO);
    }

    @PutMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Alterar produto")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Alteração do produto feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum produto alterado!")})
    public MensagemDTO alteraProduto(@PathVariable Long idProduto,
            @Valid @RequestBody ProdutoDTO produtoDTO) throws GenericException {
        return produtoService.alterarProduto(idProduto, produtoDTO);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Listar produtos")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Procura do produto feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum produto encontrado!")})
    public List<Produto> listaProduto() throws GenericException {
        return produtoService.listarProdutos();
    }

    @GetMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Consulta produto por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Procura do ID do produto feita com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum produto encontrado pelo ID!")})
    public Produto consultarPorId(@PathVariable Long idProduto) throws GenericException {
        return produtoService.consultarPorId(idProduto);
    }

    // TODO verificar possibilidade de passar nome no corpo ConsultarPeloNomeDTO
    @GetMapping(value = "/nome", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Consulta produto pelo nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Procura do nome do produto feita com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum produto encontrado pelo nome!")})
    public List<Produto> consultarPeloNome(@RequestParam("nome") String nome) {
        return produtoService.consultarPeloNome(nome);
    }

    @DeleteMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Remover produto")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Remoção do produto feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum produto removido!")})
    public MensagemDTO removeProduto(@PathVariable Long idProduto) throws GenericException {
        return produtoService.removerProduto(idProduto);
    }
}
