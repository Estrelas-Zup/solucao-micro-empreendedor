package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EstoqueDTO {
    @Positive(message = "IdProduto deve ser maior que zero!")
    @NotNull
    @NotBlank
    private long idProduto;

    @Positive(message = "Quantidade deve ser maior que zero!")
    @NotNull
    @NotBlank
    private int quantidade;

    @FutureOrPresent(message = "Data de validade não deve ser menor que data atual")
    @NotNull
    @NotBlank
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
