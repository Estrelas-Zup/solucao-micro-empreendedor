package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AberturaComercioDTO {
    
    @Positive(message = "O campo deve ser maior que zero.")
    @Size(min = 8, max = 16, message = "O campo deve conter no mínimo 8 e no máximo 16 caracteres.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double capitalSocial;

    public Double getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(Double capitalSocial) {
        this.capitalSocial = capitalSocial;
    }
    
}
