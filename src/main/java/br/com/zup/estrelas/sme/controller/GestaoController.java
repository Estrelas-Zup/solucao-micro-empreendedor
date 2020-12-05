package br.com.zup.estrelas.sme.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
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
}
