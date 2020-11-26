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
    @Email
    @NotBlank(message = "O campo email é obrigatório!")
    @Column(nullable = false)
    private String email;

  
    @NotBlank(message = "O campo é obrigatório!")
    @Size(min = 8, max = 16)
    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String role;

    public String getEmail() {
        return email;
    }


    public Usuario() {

    }


    public Usuario(String email, String senha, String role) {
        super();
        this.email = email;
        this.senha = senha;
        this.role = role;
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
