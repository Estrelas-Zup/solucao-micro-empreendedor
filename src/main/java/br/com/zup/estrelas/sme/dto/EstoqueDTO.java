package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EstoqueDTO {
    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private long idProduto;

    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private int quantidade;

    @FutureOrPresent(message = "O campo deve possuir data atual ou superior a atual.")
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
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
