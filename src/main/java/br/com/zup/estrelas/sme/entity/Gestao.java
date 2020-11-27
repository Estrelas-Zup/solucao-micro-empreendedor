package br.com.zup.estrelas.sme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Gestao {
    @Id
    @Column(name = "id_gestao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGestao;

    @Column(name = "capital_social", nullable = false)
    @Size(min = 1, max = 81000, message = "Capital social deve ser entre R$1,00 e R$81.000,00")
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
