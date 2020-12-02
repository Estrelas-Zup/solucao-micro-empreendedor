package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RemoveUsuarioDTO {
    @Email(message = "O formato do e-mail esta inválido.")
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
