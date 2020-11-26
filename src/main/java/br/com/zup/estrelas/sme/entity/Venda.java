package br.com.zup.estrelas.sme.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Venda {
    @Id
    @Column(name = "id_venda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenda;

    @Column(nullable = true)
    @NotBlank(message = "Observação não deve ter apenas espaço")
    private String observacao;

    @Column(name = "valor_desconto", columnDefinition = "DOUBLE DEFAULT 0", insertable = false,
            updatable = true)
    @PositiveOrZero(message = "Valor desconto deve ser igual ou maior a zero!")
    private Double valorDesconto;

    @Column(name = "valor_total", nullable = false)
    @Positive(message = "Valor total deve ser maior que zero!")
    private Double valorTotal;

    @Column(name = "data_venda", nullable = false)
    @FutureOrPresent(message = "Data de venda deve ser igual data atual ou superior.")
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
