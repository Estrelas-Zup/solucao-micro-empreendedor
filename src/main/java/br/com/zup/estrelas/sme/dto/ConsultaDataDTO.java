package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

public class ConsultaDataDTO {
    @NotNull(message = "O campo não pode ser vazio.")
    private LocalDate data;

    public LocalDate getData() {
        return data;
    }
}
