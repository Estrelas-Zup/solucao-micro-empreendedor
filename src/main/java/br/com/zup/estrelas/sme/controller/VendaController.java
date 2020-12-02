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
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.AdicionarVendaDTO;
import br.com.zup.estrelas.sme.dto.AlterarVendaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Venda;
import br.com.zup.estrelas.sme.service.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/vendas")
@Api(value = "Venda", description = "REST API Venda", tags = {"Venda"})
public class VendaController {

    @Autowired
    VendaService service;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Adicionar venda")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Criado com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public MensagemDTO adicionarVenda(@Valid @RequestBody AdicionarVendaDTO adicionarVendaDTO) {
        return service.adicionarVenda(adicionarVendaDTO);
    }

    @PutMapping(path = "/{idVenda}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Alterar venda")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Alteração da venda feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhuma venda alterada!")})
    public MensagemDTO alterarVenda(@PathVariable Long idVenda,
            @Valid @RequestBody AlterarVendaDTO alterarVendaDTO) {
        return service.alterarVenda(idVenda, alterarVendaDTO);
    }

    @GetMapping(path = "/{idVenda}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Buscar venda por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Procura do ID da venda feita com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhuma venda encontrada pelo ID!")})
    public Venda buscarVendaPorId(@PathVariable Long idVenda) {
        return service.buscarVendaPorId(idVenda);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Listar vendas")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Listagem de venda realizada com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhuma venda encontrada!")})
    public List<Venda> listarVendas() {
        return service.listarVendas();
    }

    @DeleteMapping(path = "/{idVenda}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Remover venda")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Remoção da venda feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhuma venda removida!")})
    public MensagemDTO removerVenda(@PathVariable Long idVenda) {
        return service.removerVenda(idVenda);
    }
}
