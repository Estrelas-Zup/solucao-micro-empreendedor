package br.com.zup.estrelas.sme.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Gestao;
import br.com.zup.estrelas.sme.repository.GestaoRepository;
import br.com.zup.estrelas.sme.service.GestaoService;

@Service
public class GestaoImpl implements GestaoService {

    private static final String BOM_TRABALHO_E_BOA_SORTE =
            "Abertura de comercio inicializada, bom trabalho e boa sorte!";
   
    @Autowired
    GestaoRepository gestaoRepository;

    @Override
    public MensagemDTO aberturaComercio(AberturaComercioDTO aberturaComercioDTO) {

        Gestao gestao = new Gestao();
        BeanUtils.copyProperties(aberturaComercioDTO, gestao);

        gestaoRepository.save(gestao);

        return new MensagemDTO(BOM_TRABALHO_E_BOA_SORTE);
    }

}
