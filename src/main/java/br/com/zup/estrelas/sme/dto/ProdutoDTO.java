package br.com.zup.estrelas.sme.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import br.com.zup.estrelas.sme.enums.UnidadeMedida;

public class ProdutoDTO {
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo não pode ser vazio.")
    private UnidadeMedida unidadeMedida;

    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double valorVenda;

    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double valorCusto;

    @PositiveOrZero(message = "O campo deve ser igual ou maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double margemDesconto;

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
