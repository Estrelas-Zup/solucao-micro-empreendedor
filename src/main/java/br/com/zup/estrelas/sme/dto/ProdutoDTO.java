package br.com.zup.estrelas.sme.dto;

import br.com.zup.estrelas.sme.enums.UnidadeMedida;

public class ProdutoDTO {

    private String nome;

    private String descricao;

    private UnidadeMedida unidadeMedida;

    private Double valorVenda;

    private Double valorCusto;

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
