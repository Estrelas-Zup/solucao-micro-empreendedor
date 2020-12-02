package br.com.zup.estrelas.sme.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Caixa {

    @Id
    @Column(name = "id_caixa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaixa;

    @Column(nullable = false)
    private LocalDate data;

    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;

    @Column(columnDefinition = "Double default 0", name = "valor_total_despesa", insertable = false,
            updatable = true)
    private Double valorTotalDespesa;

    @Column(columnDefinition = "Double default 0", name = "valor_total", insertable = false,
            updatable = true)
    private Double valorTotal;

    @Column(name = "caixa_aberto")
    private boolean caixaAberto;

    public boolean isCaixaAberto() {
        return caixaAberto;
    }

    public void setCaixaAberto(boolean caixaAberto) {
        this.caixaAberto = caixaAberto;
    }

    public Long getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(Long idCaixa) {
        this.idCaixa = idCaixa;
    }

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

    public void setValorTotalDespesa(Double valorDespesa) {
        this.valorTotalDespesa = valorDespesa;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
