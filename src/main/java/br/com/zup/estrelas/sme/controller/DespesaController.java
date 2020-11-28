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
import br.com.zup.estrelas.sme.dto.DespesaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Despesa;
import br.com.zup.estrelas.sme.service.DespesaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/despesas")
@Api(value = "Despesa", description = "REST API Despesa", tags = {"Despesa"})
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Adicionar despesa")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Despesa adiconada com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public MensagemDTO adicionarDespesa(@RequestBody DespesaDTO despesaDTO) {
        return despesaService.adicionarDespesa(despesaDTO);
    }

    @PutMapping(path = "/{idDespesa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Alterar despesa")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Alteração da despesa feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum despesa alterado!")})
    public MensagemDTO alterarDespesa(@PathVariable Long idDespesa,
            @RequestBody DespesaDTO despesaDTO) {
        return despesaService.alterarDespesa(idDespesa, despesaDTO);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Listar despesas")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Procura da despesa feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhuma despesa encontrado!")})
    public List<Despesa> listarDespesa() {
        return despesaService.listarDespesa();
    }

    @DeleteMapping(path = "/{idDespesa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Remover despesa")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Despesa removida com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhuma despesa encontrado!")})
    public MensagemDTO removerDespesa(@PathVariable Long idDespesa) {
        return despesaService.removerDespesa(idDespesa);
    }
}
