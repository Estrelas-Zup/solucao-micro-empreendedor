package br.com.zup.estrelas.sme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import br.com.zup.estrelas.sme.enums.UnidadeMedida;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(nullable = false)
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String nome;

    @Column(nullable = true)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida", nullable = false)
    @NotNull(message = "O campo não pode ser vazio.")
    private UnidadeMedida unidadeMedida;

    @Column(name = "valor_venda", nullable = false)
    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double valorVenda;

    @Column(name = "valor_custo", nullable = false)
    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double valorCusto;

    @Column(name = "margem_desconto", nullable = false)
    @PositiveOrZero(message = "O campo deve ser igual ou maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double margemDesconto;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(Double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public Double getMargemDesconto() {
        return margemDesconto;
    }

    public void setMargemDesconto(Double margemDesconto) {
        this.margemDesconto = margemDesconto;
    }
}
