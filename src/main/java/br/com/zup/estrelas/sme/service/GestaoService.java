package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.RelatorioLucroDespesaDTO;
import br.com.zup.estrelas.sme.dto.RelatorioPrejuizoUnitarioProdutoDTO;
import br.com.zup.estrelas.sme.dto.RelatorioSugestaoNovoPrecoVendaDTO;
import br.com.zup.estrelas.sme.exceptions.GenericException;

public interface GestaoService {
    public MensagemDTO aberturaComercio(AberturaComercioDTO aberturaComercioDTO);
    
    public MensagemDTO adicionarInvestimentoCapitalSocial(AberturaComercioDTO aberturaComercioDTO);
    
    public MensagemDTO encerrarComercio();
    
    public RelatorioSugestaoNovoPrecoVendaDTO calcularPrecoVendaPorProduto(Long idProdutoEstoque) throws GenericException; 
    
    public RelatorioPrejuizoUnitarioProdutoDTO calcularPrejuizoUnitarioPorProduto(Long idProduto) throws GenericException;
    
    public RelatorioLucroDespesaDTO calcularLucroDiario() throws GenericException;
    
    public RelatorioLucroDespesaDTO calcularDespesasDiaria() throws GenericException;
    
    public List<RelatorioLucroDespesaDTO> calcularLucroMensal();
    
    public List<RelatorioLucroDespesaDTO> calcularDespesasDoMes();
    
    public boolean verificarDisponibilidadeCapitalSocial(Double valor);

}
