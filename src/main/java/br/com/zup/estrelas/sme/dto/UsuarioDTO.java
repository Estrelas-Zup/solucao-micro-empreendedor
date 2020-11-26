package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;

public class UsuarioDTO {

    @Email
    private String email;

    @Positive
    private String senha;

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
