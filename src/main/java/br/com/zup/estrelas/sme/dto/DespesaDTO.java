package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DespesaDTO {

    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String descricao;

    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double valor;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
