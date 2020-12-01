package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProdutosVendaDTO {
    private Long idProduto;

    @Positive(message = "O campo deve ser maior que zero.")
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
