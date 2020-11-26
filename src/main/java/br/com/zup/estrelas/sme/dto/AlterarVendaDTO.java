package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.Positive;

public class AlterarVendaDTO {
    private String observacao;

    @Positive(message = "Valor desconto deve ser maior que zero!")
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
