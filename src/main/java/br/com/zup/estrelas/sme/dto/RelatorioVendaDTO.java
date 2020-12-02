package br.com.zup.estrelas.sme.dto;

import java.util.List;

public class RelatorioVendaDTO {

    private String nomeProduto;

    private Double valorUnitario;

    private List<InformacoesRelatorioVendaDTO> informacoesRelario;
    
    private Double valorTotal;

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public List<InformacoesRelatorioVendaDTO> getInformacoesRelario() {
        return informacoesRelario;
    }

    public void setInformacoesRelario(List<InformacoesRelatorioVendaDTO> informacoesRelario) {
        this.informacoesRelario = informacoesRelario;
    }



}
