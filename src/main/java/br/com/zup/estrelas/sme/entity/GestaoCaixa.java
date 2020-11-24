package br.com.zup.estrelas.sme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GestaoCaixa {
    @Id
    @Column(name = "id_gestao_caixa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGestaoCaixa;

    @Column(name = "capital_social", nullable = true)
    private Double capitalSocial;

    public Long getIdGestaoCaixa() {
        return idGestaoCaixa;
    }

    public void setIdGestaoCaixa(Long idGestaoCaixa) {
        this.idGestaoCaixa = idGestaoCaixa;
    }

    public Double getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(Double capitalSocial) {
        this.capitalSocial = capitalSocial;
    }
}
