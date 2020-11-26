package br.com.zup.estrelas.sme.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Venda {
    @Id
    @Column(name = "id_venda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenda;

    @Column(nullable = true)
    private String observacao;

    @Column(name = "valor_desconto", columnDefinition = "DOUBLE DEFAULT 0", insertable = false,
            updatable = true)
    private Double valorDesconto;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Column(name = "data_venda", nullable = false)
    private LocalDate dataVenda;

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
}
