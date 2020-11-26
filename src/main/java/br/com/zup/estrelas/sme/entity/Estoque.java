package br.com.zup.estrelas.sme.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Estoque {
    @Id
    @Column(name = "id_estoque")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstoque;

    @OneToOne
    @JoinColumn(name = "id_produto", foreignKey = @ForeignKey(name = "FK_PRODUTO_ESTOQUE"))
    @NotNull(message = "IdProduto não pode ser vazio!")
    @Valid
    private Produto produto;

    @Positive(message = "Quantidade não deve ser negativa!")
    @Column(nullable = false)
    private int quantidade;

    @FutureOrPresent(message = "Data de validade não deve ser menor que data atual")
    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;

    @Column(columnDefinition = "boolean default false", insertable = false, updatable = true)
    private boolean perda;

    @Column(name = "motivo_perda", nullable = true)
    private String motivoPerda;

    @Column(columnDefinition = "boolean default true", insertable = false, updatable = true)
    private boolean disponibilidade;

    public Long getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean isPerda() {
        return perda;
    }

    public void setPerda(boolean perda) {
        this.perda = perda;
    }

    public String getMotivoPerda() {
        return motivoPerda;
    }

    public void setMotivoPerda(String motivoPerda) {
        this.motivoPerda = motivoPerda;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
