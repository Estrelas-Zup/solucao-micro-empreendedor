package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AberturaComercioDTO {
    
    @Positive(message = "O campo deve ser maior que zero.")
    @Size(min = 1, max = 8100, message = "O campo deve conter entre R$1,00 a R$81.000,00.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double capitalSocial;

    public Double getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(Double capitalSocial) {
        this.capitalSocial = capitalSocial;
    }
    
}
