package br.com.zup.estrelas.sme.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.RelatorioVendaDTO;
import br.com.zup.estrelas.sme.dto.RelatorioVendaDataDTO;
import br.com.zup.estrelas.sme.entity.RelatorioVenda;
import br.com.zup.estrelas.sme.service.RelatorioVendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/relatorioVenda")
@Api(value = "RelatorioVenda", description = "REST API RelatorioVenda", tags = {"RelatorioVenda"})
public class RelatorioVendaController {

    @Autowired
    RelatorioVendaService relatorioVendaService;

    @GetMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Listar relatório Venda")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Procura do relatório feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum relatório encontrado!")})
    public RelatorioVendaDTO consultarRelatorioProduto(@PathVariable Long idProduto) {
        return relatorioVendaService.consultarRelatorioProduto(idProduto);
    }

    @GetMapping(path = "/consultaData", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Listar relatório da venda pela data inicial e pela data final")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Procura do relatório pela data inicial e pela data final feita com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum relatório encontrado!")})
    public RelatorioVendaDataDTO listarRelatorioVendaMes(
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate dataFinal) {
        return relatorioVendaService.listarRelatorioVendaMes(dataInicial, dataFinal);
    }

    @GetMapping(path = "/data", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Listar relatório da venda pela data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Procura do relatório pela data feita com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum relatório encontrado!")})
    public List<RelatorioVenda> consultarRelatorioPorData(
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate data) {
        return relatorioVendaService.consultarRelatorioVendaPorData(data);
    }
}
