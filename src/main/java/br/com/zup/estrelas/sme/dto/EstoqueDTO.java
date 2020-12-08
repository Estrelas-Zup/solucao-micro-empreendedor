package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EstoqueDTO {
    @NotNull(message = "O campo não pode ser vazio.")
    @Positive(message = "O campo deve ser maior que zero.")
    private Integer quantidade;

    @FutureOrPresent(message = "O campo deve possuir data atual ou superior a atual.")
    @NotNull(message = "O campo não pode ser vazio.")
    private LocalDate dataValidade;

    public EstoqueDTO() {
        super();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

}
