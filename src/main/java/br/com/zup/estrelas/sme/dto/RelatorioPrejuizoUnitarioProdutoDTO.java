package br.com.zup.estrelas.sme.dto;

public class RelatorioPrejuizoUnitarioProdutoDTO {
    private Double precoCusto;
    private Double precoVendaAtual;
    private Double valorPrejuizoUnitario;
    private Double valorTotalLucroPerdido;
    private int totalQuantidadePerdida;

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

    public Double getValorPrejuizoUnitario() {
        return valorPrejuizoUnitario;
    }

    public void setValorPrejuizoUnitario(Double valorPrejuizoUnitario) {
        this.valorPrejuizoUnitario = valorPrejuizoUnitario;
    }

    public Double getValorTotalLucroPerdido() {
        return valorTotalLucroPerdido;
    }

    public void setValorTotalLucroPerdido(Double valorTotalLucroPerdido) {
        this.valorTotalLucroPerdido = valorTotalLucroPerdido;
    }

    public int getTotalQuantidadePerdida() {
        return totalQuantidadePerdida;
    }

    public void setTotalQuantidadePerdida(int totalQuantidadePerdida) {
        this.totalQuantidadePerdida = totalQuantidadePerdida;
    }
    
}
