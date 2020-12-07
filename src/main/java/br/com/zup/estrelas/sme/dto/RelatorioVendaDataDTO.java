package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"idVenda", "nomeProduto", "quantidade", "valorUnitario", "valorTotal",
        "valorVenda", "data"})
public class RelatorioVendaDataDTO {

    private Long IdVenda;

    private String nomeProduto;

    private int quantidade;

    private Double valorUnitario;

    private Double valorTotal;

    private Double valorVenda;

    private LocalDate data;

    @Override
    public String toString() {
        return "RelatorioVendaDataDTO [data=" + data + "]";
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getIdVenda() {
        return IdVenda;
    }

    public void setIdVenda(Long idVenda) {
        IdVenda = idVenda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }


}
