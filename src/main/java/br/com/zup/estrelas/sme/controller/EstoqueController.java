package br.com.zup.estrelas.sme.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.ContabilizaPerdaDTO;
import br.com.zup.estrelas.sme.dto.EstoqueDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Estoque;
import br.com.zup.estrelas.sme.service.EstoqueService;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    @Autowired
    EstoqueService estoqueService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO adicionarEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        return estoqueService.adicionarEstoque(estoqueDTO);
    }

    @PutMapping(path = "/{idEstoque}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alterarEstoque(@PathVariable Long idEstoque,
            @RequestBody EstoqueDTO estoqueDTO) {
        return estoqueService.alterarEstoque(idEstoque, estoqueDTO);
    }

    @GetMapping(path = "/{idProduto}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Estoque> consultarEstoquePorProduto(@PathVariable Long idProduto) {
        return estoqueService.consultarEstoquePorProduto(idProduto);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Estoque> listarEstoque() {
        return estoqueService.listarEstoques();
    }

    @PutMapping(path = "/perda/{idEstoque}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO contablizarPerda(@PathVariable Long idEstoque,
            @RequestBody ContabilizaPerdaDTO contabilizaPerdaDTO) {
        return estoqueService.contablizarPerda(idEstoque, contabilizaPerdaDTO);
    }
}
