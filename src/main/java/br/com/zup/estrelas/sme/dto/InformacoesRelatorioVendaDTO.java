package br.com.zup.estrelas.sme.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"idVenda", "quantidade", "valorVenda"})
public class InformacoesRelatorioVendaDTO {

    private Long IdVenda;

    private int quantidade;

    private Double valorVenda;

    public Long getIdVenda() {
        return IdVenda;
    }

    public void setIdVenda(Long idVenda) {
        IdVenda = idVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

}
