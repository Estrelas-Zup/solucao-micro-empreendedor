package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;

public class ConsultaDataDTO {
    
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private LocalDate data;

    public LocalDate getData() {
        return data;
    }
}
