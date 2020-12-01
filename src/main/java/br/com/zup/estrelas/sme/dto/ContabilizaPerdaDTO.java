package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContabilizaPerdaDTO {
    @NotNull(message = "Motivo perda não pode estar vazio!")
    @NotBlank(message = "Motivo perda não pode estar em branco!")
    private String motivoPerda;

    public String getMotivoPerda() {
        return motivoPerda;
    }
}
