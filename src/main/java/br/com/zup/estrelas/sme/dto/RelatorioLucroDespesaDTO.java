package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;

public class RelatorioLucroDespesaDTO {
    private LocalDate data;
    private Double valor;
    private Double mediaMensal;

    public Double getMediaMensal() {
        return mediaMensal;
    }

    public void setMediaMensal(Double mediaMensal) {
        this.mediaMensal = mediaMensal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
