package br.com.zup.estrelas.sme.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UsuarioDTO {


    @Email(message = "O Email é obrigatorio")
    @NotBlank(message = "Email não deve ter apenas espaço.")
    private String email;

    @NotBlank(message = "Não pode conter espaço.")
    @Size(min = 8, max = 16, message = "Senha deve conterno minimo 8 caracteres e no maximo 16 ")
    @NotNull(message = "O campo não pode ficar vazio.")
    private String senha;

    @NotNull(message = "O campo não pode ficar vazio.")
    @NotBlank(message = "Não pode conter espaço.")
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
