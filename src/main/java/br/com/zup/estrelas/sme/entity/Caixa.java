package br.com.zup.estrelas.sme.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @Column(columnDefinition = "Double default 0", name = "valor_despesa")
    private Double valorDespesa;

    @Column(columnDefinition = "Double default 0", name = "valor_total")
    private Double valorTotal;

    @OneToMany(mappedBy = "caixa")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Despesa> despesas;

    @OneToMany
//    @JoinColumn(name = "id_venda", foreignKey = @ForeignKey(name = "FK_VENDA_CAIXA"))
    private List<Venda> vendas;

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

    public Double getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(Double valorDespesa) {
        this.valorDespesa = valorDespesa;
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
