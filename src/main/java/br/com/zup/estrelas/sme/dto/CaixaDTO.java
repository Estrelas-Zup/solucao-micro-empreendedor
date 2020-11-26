package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;
import java.util.List;
import br.com.zup.estrelas.sme.entity.Despesa;
import br.com.zup.estrelas.sme.entity.Venda;

public class CaixaDTO {

    private LocalDate data;

    private Double saldoInicial;

    private Double valorTotalDespesa;

    private Double valorTotal;

    private List<Despesa> despesas;

    private List<Venda> vendas;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Double getValorTotalDespesa() {
        return valorTotalDespesa;
    }

    public void setValorTotalDespesa(Double valorTotalDespesa) {
        this.valorTotalDespesa = valorTotalDespesa;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
