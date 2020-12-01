package br.com.zup.estrelas.sme.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Venda {
    @Id
    @Column(name = "id_venda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenda;

    @Column(nullable = true)
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String observacao;

    @Column(name = "valor_desconto", columnDefinition = "DOUBLE DEFAULT 0", insertable = false,
            updatable = true)
    @PositiveOrZero(message = "O campo deve ser igual ou maior a zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double valorDesconto;

    @Column(name = "valor_total", nullable = false)
    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double valorTotal;

    @Column(name = "data_venda", nullable = false)
    @FutureOrPresent(message = "O campo deve possuir data atual ou superior a atual.")
    private LocalDate dataVenda;

    @ManyToOne
    @JoinColumn(name = "id_caixa", foreignKey = @ForeignKey(name = "FK_CAIXA_VENDA"))
    @NotEmpty(message = "O campo não pode ser vazio.")
    private Caixa caixa;

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

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
}
