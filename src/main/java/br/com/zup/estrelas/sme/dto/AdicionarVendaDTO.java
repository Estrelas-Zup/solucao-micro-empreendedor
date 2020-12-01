package br.com.zup.estrelas.sme.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AdicionarVendaDTO {
    @NotBlank(message = "Observação não deve ter apenas espaço")
    private String observacao;

    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double valorDesconto;

    @NotEmpty(message = "Venda deve ter pelo menos um produto!")
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
