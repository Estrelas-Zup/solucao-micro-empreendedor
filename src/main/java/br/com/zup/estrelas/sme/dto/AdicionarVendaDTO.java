package br.com.zup.estrelas.sme.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class AdicionarVendaDTO {
    @NotBlank(message = "Observação não deve ter apenas espaço")
    private String observacao;

    @Positive(message = "Valor desconto deve ser maior que zero!")
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
