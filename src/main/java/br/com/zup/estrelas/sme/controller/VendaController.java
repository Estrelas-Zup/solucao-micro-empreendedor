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
import br.com.zup.estrelas.sme.dto.AdicionarVendaDTO;
import br.com.zup.estrelas.sme.dto.AlterarVendaDTO;
import br.com.zup.estrelas.sme.entity.Venda;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    VendaService service;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO adicionarVenda(@RequestBody AdicionarVendaDTO adicionarVendaDTO) {
        return service.adicionarVenda(adicionarVendaDTO);
    }

    @PutMapping(path = "/{idVenda}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alterarVenda(@PathVariable Long idVenda, @RequestBody AlterarVendaDTO alterarVendaDTO) {
        return service.alterarVenda(idVenda, alterarVendaDTO);
    }

    @GetMapping(path = "/{idVenda}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Venda buscarVendaPorId(@PathVariable Long idVenda) {
        return service.buscarVendaPorId(idVenda);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Venda> listarVendas() {
        return service.listarVendas();
    }
    
    @DeleteMapping(path = "/{idVenda}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO removerVenda(@PathVariable Long idVenda) {
        return service.removerVenda(idVenda);
    }
}
