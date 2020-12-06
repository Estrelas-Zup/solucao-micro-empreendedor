package br.com.zup.estrelas.sme.dto;

public class RelatorioSugestaoNovoPrecoVendaDTO {
    private Double precoCusto;
    private Double precoVendaAtual;
    private Double sugestaoNovoPrecoVenda;
    private Integer totalQuantidadeProduzida;
    private Integer totalQuantidadeVendida;
    private Integer totalQuantidadePerdida;

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

    public Double getSugestaoNovoPrecoVenda() {
        return sugestaoNovoPrecoVenda;
    }

    public void setSugestaoNovoPrecoVenda(Double sugestaoNovoPrecoVenda) {
        this.sugestaoNovoPrecoVenda = sugestaoNovoPrecoVenda;
    }

    public Integer getTotalQuantidadeProduzida() {
        return totalQuantidadeProduzida;
    }

    public void setTotalQuantidadeProduzida(Integer totalQuantidadeProduzida) {
        this.totalQuantidadeProduzida = totalQuantidadeProduzida;
    }

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

}
