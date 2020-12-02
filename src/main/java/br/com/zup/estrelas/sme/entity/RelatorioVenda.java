package br.com.zup.estrelas.sme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RelatorioVenda {
    @Id
    @Column(name = "id_relatorio_venda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRelatorioVenda;

    @ManyToOne
    @JoinColumn(name = "id_venda", foreignKey = @ForeignKey(name = "FK_VENDA_RELATORIOVENDA"))
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "id_estoque", foreignKey = @ForeignKey(name = "FK_ESTOQUE_RELATORIOVENDA"))
    private Estoque estoque;

    @Column(nullable = false)
    private int quantidade;

    public Long getIdRelatorioVenda() {
        return idRelatorioVenda;
    }

    public void setIdRelatorioVenda(Long idRelatorioVenda) {
        this.idRelatorioVenda = idRelatorioVenda;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
