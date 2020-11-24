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

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO adicionarDespesa(@RequestBody DespesaDTO despesa) {
        return despesaService.adicionarDespesa(despesa);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Despesa> listaDespesa() {
        return despesaService.listarDespesa();
    }

    @PutMapping(path = "/{idDespesa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alteraDespesa(@PathVariable Long idDespesa,
            @RequestBody DespesaDTO despesaDTO) {
        return despesaService.alterarDespesa(idDespesa, despesaDTO);
    }

    @DeleteMapping(path = "/{idDespesa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO removeDespesa(@PathVariable Long idDespesa) {
        return despesaService.removerDespesa(idDespesa);
    }
}
