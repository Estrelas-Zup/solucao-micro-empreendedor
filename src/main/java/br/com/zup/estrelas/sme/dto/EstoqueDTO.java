package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EstoqueDTO {
    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Long idProduto;

    @NotNull(message = "O campo não pode ser vazio.")
    @Positive(message = "O campo deve ser maior que zero.")
    private Integer quantidade;

    @FutureOrPresent(message = "O campo deve possuir data atual ou superior a atual.")
    @NotNull(message = "O campo não pode ser vazio.")
    // @Pattern(regexp = "^[0-9]{4}-((1[0-2])|([1-9]))-(([0-2][0-9])|([1-9])|(3[0-1]))$", message = "O formato de data esta inválido.")
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
