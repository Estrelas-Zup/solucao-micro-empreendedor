package br.com.zup.estrelas.sme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Gestao {
    @Id
    @Column(name = "id_gestao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGestao;

    @Column(name = "capital_social", nullable = false)
    @Positive(message = "O campo deve ser maior que zero.")
    @Size(min = 1, max = 8100, message = "O campo deve conter entre R$1,00 a R$81.000,00.")
    @NotNull(message = "O campo não pode ser vazio.")
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
