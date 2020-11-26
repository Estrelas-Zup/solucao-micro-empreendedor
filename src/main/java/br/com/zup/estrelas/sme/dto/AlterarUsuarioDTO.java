package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AlterarUsuarioDTO {

    @NotBlank(message = "Não pode conter espaço.")
    @Size(min = 8, max = 16, message = "Senha deve conterno minimo 8 caracteres e no maximo 16 ")
    @NotNull(message = "O campo não pode ficar vazio.")
    private String senha;

    @NotNull(message = "O campo não pode ficar vazio.")
    @NotBlank(message = "Não pode conter espaço.")
    private String role;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
