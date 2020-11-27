package br.com.zup.estrelas.sme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Despesa {
    @Id
    @Column(name = "id_despesa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDespesa;

    @Column(nullable = false)
    @NotBlank(message = "Descrição não pode estar em branco!")
    private String descricao;

    @Column(nullable = false)
    @NotBlank(message = "Valor não pode estar em branco!")
    @Positive(message = "Valor deve ser maior que zero!")
    private Double valor;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_caixa", foreignKey = @ForeignKey(name = "FK_CAIXA_DESPESA"))
    private Caixa caixa;

    public Long getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Long idDespesa) {
        this.idDespesa = idDespesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
}
