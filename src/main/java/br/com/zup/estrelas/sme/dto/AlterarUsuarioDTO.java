package br.com.zup.estrelas.sme.dto;

public class AlterarUsuarioDTO {
    private String senha;
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
