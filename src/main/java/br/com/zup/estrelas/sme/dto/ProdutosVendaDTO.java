package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotBlank;

public class ProdutosVendaDTO {
    private Long idProduto;

    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private int quantidade;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
