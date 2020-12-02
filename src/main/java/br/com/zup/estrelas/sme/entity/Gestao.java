package br.com.zup.estrelas.sme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gestao {
    @Id
    @Column(name = "id_gestao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGestao;

    @Column(name = "capital_social", nullable = false)
    private Double capitalSocial;

    public Long getIdGestao() {
        return idGestao;
    }

    public void setIdGestao(Long idGestao) {
        this.idGestao = idGestao;
    }

    public Double getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(Double capitalSocial) {
        this.capitalSocial = capitalSocial;
    }
}
