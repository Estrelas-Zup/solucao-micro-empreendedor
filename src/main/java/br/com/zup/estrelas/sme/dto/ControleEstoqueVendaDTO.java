package br.com.zup.estrelas.sme.dto;

import br.com.zup.estrelas.sme.entity.Estoque;

public class ControleEstoqueVendaDTO {
    public Estoque estoque;
    public Integer quantidade;

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
