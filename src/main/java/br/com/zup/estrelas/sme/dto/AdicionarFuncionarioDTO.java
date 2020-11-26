package br.com.zup.estrelas.sme.dto;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AdicionarFuncionarioDTO {

    @NotNull(message = "O campo não pode ficar em branco")
    @NotBlank(message = "O campo nome é obrigatório com caracteres!")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O campo não pode ficar em branco")
    @NotBlank(message = "O campo CPF é obrigatório com números!")
    @Max(value = 11)
    @Column(nullable = false, unique = false, length = 11)
    private String cpf;

    @NotNull(message = "O campo não pode ficar em branco")
    @NotBlank(message = "O campo endereço é obrigatório caracteres!")
    @Column(nullable = false)
    private String endereco;

    @NotNull(message = "O campo não pode ficar em branco")
    @Past(message = "Coloque uma data valida!")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotNull(message = "O campo não pode ficar em branco")
    @Size(min = 10, max = 11, message = "numeros não validos")
    @Column(nullable = false)
    private String telefone;

    @NotNull(message = "O campo não pode ficar em branco")
    @NotBlank(message = "O campo email é obrigatório com caracteres!")
    @Email(message = "O campo email esta com formato invalido.")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "O campo não pode ficar em branco")
    @NotBlank(message = "O campo Cargo é obrigatório caracteres!")
    @Column(nullable = false)
    private String cargo;

    @NotNull(message = "O campo não pode ficar em branco")
    @Positive(message = "O Salário deve ser maior que zero!")
    @Column(nullable = false)
    private Double salario;

    @NotNull(message = "O campo não pode ficar em branco")
    @NotBlank(message = "O Campo número da carteira de trabalho é obrigatório com numeros!")
    @Column(name = "numero_carteira_trabalho", nullable = false, unique = false)
    private String numeroCarteiraTrabalho;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
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

    public String getNumeroCarteiraTrabalho() {
        return numeroCarteiraTrabalho;
    }

    public void setNumeroCarteiraTrabalho(String numeroCarteiraTrabalho) {
        this.numeroCarteiraTrabalho = numeroCarteiraTrabalho;
    }

}
