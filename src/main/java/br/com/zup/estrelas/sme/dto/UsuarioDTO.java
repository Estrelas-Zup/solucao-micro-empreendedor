package br.com.zup.estrelas.sme.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UsuarioDTO {
    @Email(message = "O Email é obrigatorio")
    @NotBlank(message = "O campo não pode ficar vazio ou conter espaço.")
    private String email;

    @NotBlank(message = "O campo não pode ficar vazio ou conter espaço.")
    @Size(min = 8, max = 16, message = "Senha deve conterno minimo 8 caracteres e no maximo 16 ")
    private String senha;

    @NotBlank(message = "O campo não pode ficar vazio ou conter espaço.")
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
