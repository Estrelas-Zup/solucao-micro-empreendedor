package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.Size;

public class AberturaComercioDTO {
    
    @Size(min = 1, max = 81000, message = "Capital social deve ser entre R$1,00 e R$81.000,00")
    private Double capitalSocial;

    public Double getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(Double capitalSocial) {
        this.capitalSocial = capitalSocial;
    }
    
}
