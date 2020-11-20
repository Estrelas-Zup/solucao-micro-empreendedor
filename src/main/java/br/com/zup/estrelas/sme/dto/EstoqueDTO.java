package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;

public class EstoqueDTO {

    private long idProduto;
    private int quantidade;
    private LocalDate dataValidade;
    private boolean perda;
    private String motivoPerda;
    private boolean disponibilidade;
    
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

    public boolean isPerda() {
        return perda;
    }

    public void setPerda(boolean perda) {
        this.perda = perda;
    }

    public String getMotivoPerda() {
        return motivoPerda;
    }

    public void setMotivoPerda(String motivoPerda) {
        this.motivoPerda = motivoPerda;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

}
