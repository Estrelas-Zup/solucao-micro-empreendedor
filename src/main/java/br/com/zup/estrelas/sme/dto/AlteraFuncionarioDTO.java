package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AlteraFuncionarioDTO {

    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String nome;

    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String endereco;

    @Size(min = 10, max = 11,
            message = "O campo deve conter no mínimo 10 e no máximo 11 caracteres.")
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private Double telefone;

    @Email(message = "O campo email esta invalido.")
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String email;

    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String cargo;

    @NotNull(message = "O campo não pode ser vazio.")
    @Positive(message = "O campo deve ser maior que zero.")
    private Double salario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getTelefone() {
        return telefone;
    }

    public void setTelefone(Double telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

}
