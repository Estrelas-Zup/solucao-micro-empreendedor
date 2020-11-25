package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;

public class EstoqueDTO {
    private long idProduto;
    private int quantidade;
    private LocalDate dataValidade;

    public EstoqueDTO() {
        super();
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

}
