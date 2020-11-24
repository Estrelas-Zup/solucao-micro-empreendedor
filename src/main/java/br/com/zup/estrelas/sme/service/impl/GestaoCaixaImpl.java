package br.com.zup.estrelas.sme.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.GestaoCaixa;
import br.com.zup.estrelas.sme.repository.GestaoCaixaRepository;
import br.com.zup.estrelas.sme.service.GestaoCaixaService;

@Service
public class GestaoCaixaImpl implements GestaoCaixaService {

    @Autowired
    GestaoCaixaRepository gestaoRepository;
    
    @Override
    public MensagemDTO aberturaComercio(AberturaComercioDTO aberturaComercioDTO) {
        
        GestaoCaixa gestao = new GestaoCaixa();
        BeanUtils.copyProperties(aberturaComercioDTO, gestao);
        
        gestaoRepository.save(gestao);
        
        
        return new MensagemDTO("cadastrou");
    }

}
