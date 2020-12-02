package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDTO {
    @Email(message = "O formato do e-mail esta inválido.")
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String email;

    @Size(min = 8, max = 16, message = "O campo deve conter no mínimo 8 e no máximo 16 caracteres.")
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String senha;

    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String role;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getRole() {
        return role;
    }
}
