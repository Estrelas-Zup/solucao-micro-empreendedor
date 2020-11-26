package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.Positive;

public class CaixaDTO {
    
    @Positive
    private Double saldoInicial;
    
    
    public Double getSaldoInicial() {
        return saldoInicial;
    }
}
