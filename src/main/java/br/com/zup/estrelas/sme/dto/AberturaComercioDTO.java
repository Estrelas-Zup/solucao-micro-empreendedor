package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AberturaComercioDTO {
  
    @DecimalMax(value = "81.000", message ="O campo não pode ter valor acima de R$81.000")
    @DecimalMin(value = "1.0", message ="O campo não pode ter valor abaixo de R$1.0")
    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double capitalSocial;

    public Double getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(Double capitalSocial) {
        this.capitalSocial = capitalSocial;
    }

}
