package br.com.zup.estrelas.sme.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class AdicionarVendaDTO {
    private String observacao;

    @PositiveOrZero(message = "O campo deve ser igual ou maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double valorDesconto;

    @NotEmpty(message = "Venda deve ter pelo menos um produto.")
    @Valid
    private List<ProdutosVendaDTO> produtosVenda;

    public String getObservacao() {
        return observacao;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public List<ProdutosVendaDTO> getProdutosVenda() {
        return produtosVenda;
    }

}
