package br.com.zup.estrelas.sme.controller;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.CaixaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Caixa;
import br.com.zup.estrelas.sme.exceptions.GenericException;
import br.com.zup.estrelas.sme.service.CaixaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/caixas")
@Api(value = "Caixa", description = "REST API Caixa", tags = {"Caixa"})
public class CaixaController {

    @Autowired
    CaixaService caixaService;

    @ApiOperation(value = "Adicionar caixa")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Criado com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public MensagemDTO adicionarCaixa(@Valid @RequestBody CaixaDTO caixaDTO)
            throws GenericException {
        return caixaService.adicionarCaixa(caixaDTO);
    }

    @ApiOperation(value = "Listar caixas")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Listagem do caixa realizada com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum caixa encontrado.")})
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Caixa> listarCaixa() throws GenericException {
        return caixaService.listarCaixa();
    }

    @ApiOperation(value = "Consultar caixa por ID")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Procura ID do caixa feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum caixa encontrado pelo ID.")})
    @GetMapping(path = "/{idCaixa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Caixa consultarPorId(@PathVariable Long idCaixa) throws GenericException {
        return caixaService.consultarPorId(idCaixa);
    }

    @ApiOperation(value = "Consultar caixa por data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta caixa por data realizada com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum caixa nesta data encontrado.")})
    @GetMapping(path = "/datas", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Caixa consultarCaixaPorData(
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate data) {
        return caixaService.consultarCaixaPorData(data);
    }

    @ApiOperation(value = "Fechar caixa")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Fechamento do caixa feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum caixa fechado.")})
    @PutMapping(path = "/{idCaixa}/fechamento", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO fechamentoCaixa(@PathVariable Long idCaixa) throws GenericException {
        return caixaService.fechamentoCaixa(idCaixa);
    }
}
