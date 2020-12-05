package br.com.zup.estrelas.sme.service;

import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;

public interface GestaoService {
    public MensagemDTO aberturaComercio(AberturaComercioDTO aberturaComercioDTO);
    
    public MensagemDTO adicionarInvestimentoCapitalSocial(AberturaComercioDTO aberturaComercioDTO);
   // public GestaoDTO calcularPrecoVendaPorProduto(Long idProduto); 
}
