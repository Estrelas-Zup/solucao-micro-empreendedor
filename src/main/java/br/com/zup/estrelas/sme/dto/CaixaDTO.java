package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CaixaDTO {
  
    @Positive(message = "Saldo inicial deve ser maior que zero!")
    @NotNull(message = "Saldo inicial não pode ser vazio!")
    @NotBlank(message = "Saldo inicial não pode estar em branco!")
    private Double saldoInicial;

    public Double getSaldoInicial() {
        return saldoInicial;
    }
}
