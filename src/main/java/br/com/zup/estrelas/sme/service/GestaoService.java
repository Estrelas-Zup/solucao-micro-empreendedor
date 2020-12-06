package br.com.zup.estrelas.sme.service;

import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.RelatorioSugestaoNovoPrecoVendaDTO;

public interface GestaoService {
    public MensagemDTO aberturaComercio(AberturaComercioDTO aberturaComercioDTO);
    
    public MensagemDTO adicionarInvestimentoCapitalSocial(AberturaComercioDTO aberturaComercioDTO);
    
    public MensagemDTO encerrarComercio();
    
    public RelatorioSugestaoNovoPrecoVendaDTO calcularPrecoVendaPorProduto(Long idProdutoEstoque); 
}
