package br.com.zup.estrelas.sme.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Venda {
    @Id
    @Column(name = "numero_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroPedido;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = true)
    private String observacao;

    @OneToMany
    @JoinColumn(name = "id_estoque")
    private List<Estoque> estoques;

    @Column(name = "valor_total_produtos", nullable = false)
    private Double valorTotalProdutos;

    @Column(name = "valor_desconto", columnDefinition = "DOUBLE DEFAULT 0")
    private Double valorDesconto;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Column(name = "data_venda", nullable = false)
    private LocalDate dataVenda;

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    public Double getValorTotalProdutos() {
        return valorTotalProdutos;
    }

    public void setValorTotalProdutos(Double valorTotalProdutos) {
        this.valorTotalProdutos = valorTotalProdutos;
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
}
