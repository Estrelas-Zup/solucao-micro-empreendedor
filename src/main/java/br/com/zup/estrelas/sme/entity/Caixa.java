package br.com.zup.estrelas.sme.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
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

    @Positive(message = "Saldo inicial deve ser maior que zero")
    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;

    @Positive(message = "Saldo inicial deve ser maior que zero")
    @Column(columnDefinition = "Double default 0", name = "valor_total_despesa", insertable = false,
            updatable = true)
    private Double valorTotalDespesa;

    @Positive(message = "Valor total deve ser maior que zero")
    @Column(columnDefinition = "Double default 0", name = "valor_total", insertable = false,
            updatable = true)
    private Double valorTotal;

    @NotEmpty(message = "")
    @OneToMany(mappedBy = "caixa")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Despesa> despesas;

    @OneToMany
    // @JoinColumn(name = "id_venda", foreignKey = @ForeignKey(name = "FK_VENDA_CAIXA"))
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

    public Double getValorTotalDespesa() {
        return valorTotalDespesa;
    }

    public void setValorDespesa(Double valorDespesa) {
        this.valorTotalDespesa = valorDespesa;
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
