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
import br.com.zup.estrelas.sme.dto.ConsultaDataDTO;
import br.com.zup.estrelas.sme.dto.CaixaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Caixa;
import br.com.zup.estrelas.sme.service.CaixaService;

@RestController
@RequestMapping("/caixas")
public class CaixaController {

    @Autowired
    CaixaService caixaService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO adicionarCaixa(@RequestBody CaixaDTO caixaDTO) {
        return caixaService.adicionarCaixa(caixaDTO);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Caixa> listaCaixa() {
        return caixaService.listarCaixa();
    }

    @GetMapping(path = "/{idCaixa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Caixa consultarPorId(@PathVariable Long idCaixa) {
        return caixaService.consultarPorId(idCaixa);
    }

    @GetMapping(path = "/data", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Caixa> consultarPelaData(@RequestBody ConsultaDataDTO dataDTO) {
        return caixaService.consultarPelaData(dataDTO);
    }

    @PutMapping(path = "/{idCaixa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alteraCaixa(@PathVariable Long idCaixa, @RequestBody CaixaDTO caixaDTO) {
        return caixaService.alteraCaixa(idCaixa, caixaDTO);
    }

    @DeleteMapping(path = "/{idCaixa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO removeCaixa(@PathVariable Long idCaixa) {
        return caixaService.removerCaixa(idCaixa);
    }

}
