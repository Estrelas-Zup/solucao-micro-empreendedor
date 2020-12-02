package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class AlterarVendaDTO {
    private String observacao;

    @PositiveOrZero(message = "O campo deve ser igual ou maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double valorDesconto;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }


}
