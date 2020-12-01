package br.com.zup.estrelas.sme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
    @Id
    @Column(nullable = false)
    @Email(message = "O campo email esta invalido.")
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String email;

    @Column(nullable = false)
    @Size(min = 8, max = 16, message = "O campo deve conter no mínimo 8 e no máximo 16 caracteres.")
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String senha;

    @Column(nullable = false)
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
