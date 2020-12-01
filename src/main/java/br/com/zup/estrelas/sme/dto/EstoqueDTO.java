package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EstoqueDTO {
    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Long idProduto;

    // @Pattern(regexp = "[1-9]*", message = "O campo deve ser maior que zero.")
    private int quantidade;

    @FutureOrPresent(message = "O campo deve possuir data atual ou superior a atual.")
    @NotNull(message = "O campo não pode ser vazio.")
    private LocalDate dataValidade;

    public EstoqueDTO() {
        super();
    }

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

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

}
