package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RemoveUsuarioDTO {

    @Email(message = "O Email é obrigatorio")
    @NotBlank(message = "Email não deve ter apenas espaço.")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
