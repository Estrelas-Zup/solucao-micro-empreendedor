package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CaixaDTO {
    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double saldoInicial;

    public Double getSaldoInicial() {
        return saldoInicial;
    }
}
