package br.com.zup.estrelas.sme.dto;

import java.util.List;

public class AdicionarVendaDTO {
    private int quantidade;
    private String observacao;
    private Double valorDesconto;
    private List<ProdutosVendaDTO> produtosVenda;

    public int getQuantidade() {
        return quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public List<ProdutosVendaDTO> getProdutosVenda() {
        return produtosVenda;
    }

}
