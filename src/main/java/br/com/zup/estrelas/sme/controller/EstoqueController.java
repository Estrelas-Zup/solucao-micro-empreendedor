package br.com.zup.estrelas.sme.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.ContabilizaPerdaDTO;
import br.com.zup.estrelas.sme.dto.EstoqueDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Estoque;
import br.com.zup.estrelas.sme.service.EstoqueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/estoques")
@Api(value = "Estoque")
public class EstoqueController {

    @Autowired
    EstoqueService estoqueService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Adicionar estoque")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Criado com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public MensagemDTO adicionarEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        return estoqueService.adicionarEstoque(estoqueDTO);
    }

    @PutMapping(path = "/{idEstoque}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Alterar estoque")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Alteração do estoque feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum estoque alterado!")})
    public MensagemDTO alterarEstoque(@PathVariable Long idEstoque,
            @RequestBody EstoqueDTO estoqueDTO) {
        return estoqueService.alterarEstoque(idEstoque, estoqueDTO);
    }

    @GetMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Buscar estoque por produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca no estoque do produto feita com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum produto encontrado pelo estoque!")})
    public List<Estoque> consultarEstoquePorProduto(@PathVariable Long idProduto) {
        return estoqueService.consultarEstoquePorProduto(idProduto);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Listar estoque")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Listagem de estoque realizada com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum estoque encontrado!")})
    public List<Estoque> listarEstoque() {
        return estoqueService.listarEstoques();
    }

    @PutMapping(path = "/perda/{idEstoque}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Contabilizar Perda")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Alteração do estoque feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum estoque alterado!")})
    public MensagemDTO contabilizarPerda(@PathVariable Long idEstoque,
            @RequestBody ContabilizaPerdaDTO contabilizaPerdaDTO) {
        return estoqueService.contablizarPerda(idEstoque, contabilizaPerdaDTO);
    }
}
