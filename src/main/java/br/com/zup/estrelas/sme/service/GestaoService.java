package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.RelatorioLucroDespesaDTO;
import br.com.zup.estrelas.sme.dto.RelatorioPrejuizoUnitarioProdutoDTO;
import br.com.zup.estrelas.sme.dto.RelatorioSugestaoNovoPrecoVendaDTO;

public interface GestaoService {
    public MensagemDTO aberturaComercio(AberturaComercioDTO aberturaComercioDTO);
    
    public MensagemDTO adicionarInvestimentoCapitalSocial(AberturaComercioDTO aberturaComercioDTO);
    
    public MensagemDTO encerrarComercio();
    
    public RelatorioSugestaoNovoPrecoVendaDTO calcularPrecoVendaPorProduto(Long idProdutoEstoque); 
    
    public RelatorioPrejuizoUnitarioProdutoDTO calcularPrejuizoUnitarioPorProduto(Long idProduto);
    
    public RelatorioLucroDespesaDTO calcularLucroDiario();
    
    public RelatorioLucroDespesaDTO calcularDespesasDiaria();
    
    public List<RelatorioLucroDespesaDTO> calcularLucroMensal();
    
    public List<RelatorioLucroDespesaDTO> calcularDespesasDoMes();

}
