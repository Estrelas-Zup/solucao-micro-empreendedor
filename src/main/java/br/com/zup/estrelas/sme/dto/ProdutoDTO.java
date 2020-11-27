package br.com.zup.estrelas.sme.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import br.com.zup.estrelas.sme.enums.UnidadeMedida;

public class ProdutoDTO {

    @NotBlank(message = "Nome não pode ter apenas espaços!")
    @NotNull(message = "O campo nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Descrição não pode ter apenas espaços!")
    private String descricao;

    @NotBlank(message = "Unidade de medida não pode ter apenas espaços!")
    @NotNull(message = "O campo unidade de medida é obrigatório!")
    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    @Positive(message = "Valor venda deve ser maior que zero!")
    private Double valorVenda;

    @Positive(message = "Valor custo deve ser maior que zero!")
    private Double valorCusto;

    @PositiveOrZero(message = "Margem de desconto deve ser igual ou maior a zero!")
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
