package br.com.zup.estrelas.sme.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AlteraFuncionarioDTO {

    @NotNull(message = "O campo não pode ficar em branco")
    @NotBlank(message = "O campo nome é obrigatório com caracteres!")
    private String nome;

    @NotNull(message = "O campo não pode ficar em branco")
    @NotBlank(message = "O campo endereço é obrigatório caracteres!")
    private String endereco;

    @NotNull(message = "O campo não pode ficar em branco")
    @Size(min = 10, max = 11, message = "numeros não validos com números")
    private Double telefone;

    @NotNull(message = "O campo não pode ficar em branco")
    @NotBlank(message = "O campo email é obrigatório com caracteres!")
    @Email(message = "O campo email esta com formato invalido.")
    private String email;

    @NotNull(message = "O campo não pode ficar em branco")
    @NotBlank(message = "O campo Cargo é obrigatório com caracteres!")
    private String cargo;

    @NotNull(message = "O campo não pode ficar em branco")
    @Positive(message = "O Salário deve ser maior que zero!")
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
