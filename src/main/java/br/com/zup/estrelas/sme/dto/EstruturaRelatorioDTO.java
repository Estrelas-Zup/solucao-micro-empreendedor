package br.com.zup.estrelas.sme.dto;

public class EstruturaRelatorioDTO {
    Integer totalQuantidadeVendida;
    Integer totalQuantidadePerdida;
    Integer totalQuantidadeProduzida;

    Double precoCusto;
    Double precoVendaAtual;

    public Integer getTotalQuantidadeVendida() {
        return totalQuantidadeVendida;
    }

    public void setTotalQuantidadeVendida(Integer totalQuantidadeVendida) {
        this.totalQuantidadeVendida = totalQuantidadeVendida;
    }

    public Integer getTotalQuantidadePerdida() {
        return totalQuantidadePerdida;
    }

    public void setTotalQuantidadePerdida(Integer totalQuantidadePerdida) {
        this.totalQuantidadePerdida = totalQuantidadePerdida;
    }

    public Integer getTotalQuantidadeProduzida() {
        return totalQuantidadeProduzida;
    }

    public void setTotalQuantidadeProduzida(Integer totalQuantidadeProduzida) {
        this.totalQuantidadeProduzida = totalQuantidadeProduzida;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoVendaAtual() {
        return precoVendaAtual;
    }

    public void setPrecoVendaAtual(Double precoVendaAtual) {
        this.precoVendaAtual = precoVendaAtual;
    }

}
