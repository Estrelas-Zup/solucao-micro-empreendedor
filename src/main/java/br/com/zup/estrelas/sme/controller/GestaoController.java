package br.com.zup.estrelas.sme.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.RelatorioLucroDespesaDTO;
import br.com.zup.estrelas.sme.dto.RelatorioPrejuizoUnitarioProdutoDTO;
import br.com.zup.estrelas.sme.dto.RelatorioSugestaoNovoPrecoVendaDTO;
import br.com.zup.estrelas.sme.exceptions.GenericException;
import br.com.zup.estrelas.sme.service.GestaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/gestao")
@Api(value = "Gestão", description = "REST API Gestão", tags = {"Gestão"})
public class GestaoController {

    @Autowired
    GestaoService gestaoService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Adicionar gestão")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Criado com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
    @ResponseStatus(HttpStatus.CREATED)
    public MensagemDTO aberturaComercio(@Valid @RequestBody AberturaComercioDTO aberturaComercioDTO)
            throws GenericException {
        return gestaoService.aberturaComercio(aberturaComercioDTO);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Alterar capital social")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Alteração de gestao feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Gestão não alterada!")})
    public MensagemDTO adicionarInvestimentoCapitalSocial(
            @Valid @RequestBody AberturaComercioDTO aberturaComercioDTO) throws GenericException {
        return gestaoService.adicionarInvestimentoCapitalSocial(aberturaComercioDTO);
    }

    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Remover comercio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Remoção do comercio realizado com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum comercio encerrado!")})
    public MensagemDTO encerrarComercio() {
        return gestaoService.encerrarComercio();
    }

    @ApiOperation(value = "Relatorio para sugerir alteração em preço de venda")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Relatorio realizado com sucesso!")})
    @GetMapping(path = "/{idProdutoEstoque}/novoPrecoVenda",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RelatorioSugestaoNovoPrecoVendaDTO calcularPrecoVendaPorProduto(
            @PathVariable Long idProdutoEstoque) throws GenericException {
        return gestaoService.calcularPrecoVendaPorProduto(idProdutoEstoque);
    }

    @ApiOperation(value = "Relatorio para mostrar prejuizo de um produto")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Relatorio realizado com sucesso!")})
    @GetMapping(path = "/{idProduto}/prejuizoUnitario",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RelatorioPrejuizoUnitarioProdutoDTO calcularPrejuizoUnitarioPorProduto(
            @PathVariable Long idProduto) throws GenericException {
        return gestaoService.calcularPrejuizoUnitarioPorProduto(idProduto);
    }

    @ApiOperation(value = "Relatorio para mostrar lucro diario")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Relatorio realizado com sucesso!")})
    @GetMapping(path = "/lucroDiario", produces = {MediaType.APPLICATION_JSON_VALUE})
    public RelatorioLucroDespesaDTO calcularLucroDiario() throws GenericException {
        return gestaoService.calcularLucroDiario();
    }

    @ApiOperation(value = "Relatorio para mostrar despesa diaria")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Relatorio realizado com sucesso!")})
    @GetMapping(path = "/despesasDiaria", produces = {MediaType.APPLICATION_JSON_VALUE})
    public RelatorioLucroDespesaDTO calcularDespesaDiaria() throws GenericException {
        return gestaoService.calcularDespesasDiaria();
    }

    @ApiOperation(value = "Relatorio para mostrar lucro mensal")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Relatorio realizado com sucesso!")})
    @GetMapping(path = "/lucrosMensais", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RelatorioLucroDespesaDTO> calcularLucroMensal() {
        return gestaoService.calcularLucroMensal();
    }

    @ApiOperation(value = "Relatorio para mostrar despesa mensal")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Relatorio realizado com sucesso!")})
    @GetMapping(path = "/despesasMensais", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RelatorioLucroDespesaDTO> calcularDespesasDoMes() throws GenericException {
        return gestaoService.calcularDespesasDoMes();
    }
    
    @ApiOperation(value = "Verificar disponibilidade no capital social")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Verificação disponibilidade em meu capital social realizado com sucesso!")})
    @GetMapping(path = "{valor}/disponibilidadeCapitalSocial", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean verificarDisponibilidadeCapitalSocial(@PathVariable Double valor) throws GenericException {
        return gestaoService.verificarDisponibilidadeCapitalSocial(valor);
    }
}
