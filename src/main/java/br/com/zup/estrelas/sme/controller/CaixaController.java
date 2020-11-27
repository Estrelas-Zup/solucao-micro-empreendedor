package br.com.zup.estrelas.sme.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.ConsultaDataDTO;
import br.com.zup.estrelas.sme.dto.CaixaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Caixa;
import br.com.zup.estrelas.sme.service.CaixaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Caixa")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/caixas")
public class CaixaController {

    @Autowired
    CaixaService caixaService;

    @ApiOperation(value = "Adicionar caixa")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO adicionarCaixa(@RequestBody CaixaDTO caixaDTO) {
        return caixaService.adicionarCaixa(caixaDTO);
    }

    @ApiOperation(value = "Listar caixa")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Caixa> listarCaixa() {
        return caixaService.listarCaixa();
    }

    @ApiOperation(value = "Consultar caixa por ID")
    @GetMapping(path = "/{idCaixa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Caixa consultarPorId(@PathVariable Long idCaixa) {
        return caixaService.consultarPorId(idCaixa);
    }

    @ApiOperation(value = "Consultar caixa por data")
    @GetMapping(path = "/datas", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Caixa> consultarCaixaPorData(@RequestBody ConsultaDataDTO consultaDataDTO) {
        return caixaService.consultarCaixaPorData(consultaDataDTO);
    }

    @ApiOperation(value = "Alterar caixa")
    @PutMapping(path = "/{idCaixa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alterarCaixa(@PathVariable Long idCaixa, @RequestBody CaixaDTO caixaDTO) {
        return caixaService.alterarCaixa(idCaixa, caixaDTO);
    }

    @ApiOperation(value = "Remover caixa")
    @DeleteMapping(path = "/{idCaixa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO removerCaixa(@PathVariable Long idCaixa) {
        return caixaService.removerCaixa(idCaixa);
    }

}
