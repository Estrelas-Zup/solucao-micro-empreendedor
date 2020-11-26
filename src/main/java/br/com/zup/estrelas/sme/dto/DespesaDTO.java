package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DespesaDTO {
    @NotNull(message = "Descrição não pode ser vazia!")
    @NotBlank(message = "Descrição não pode estar em branco!")
    private String descricao;

    @NotNull(message = "Valor não pode ser vazia!")
    @NotBlank(message = "Valor não pode estar em branco!")
    @Positive(message = "Valor deve ser maior que zero!")
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
