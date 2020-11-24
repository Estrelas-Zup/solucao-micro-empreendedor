package br.com.zup.estrelas.sme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.service.GestaoCaixaService;

@RestController
@RequestMapping("/gestao")
public class GestaoCaixaController {

    @Autowired
    GestaoCaixaService gestaoCaixaService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO aberturaComercio(@RequestBody AberturaComercioDTO aberturaComercioDTO) {
        return gestaoCaixaService.aberturaComercio(aberturaComercioDTO);
    }


}
