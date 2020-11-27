package br.com.zup.estrelas.sme.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long idFuncionario;


    @NotNull(message = "O campo nome não pode ficar em branco")
    @NotBlank(message = "O campo nome é obrigatório!")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O campo email não pode ficar em branco")
    @NotBlank(message = "O campo email é obrigatório!")
    @Email(message = "O campo email esta com formato invalido.")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "O campo telefone não pode ficar em branco")
    @Size(min = 10, max = 11, message = "O campo telefone deve ter entre 10 e 11 caracteres")
    @Column(nullable = false)
    private String telefone;

    @NotNull(message = "O campo data nascimetno não pode ficar em branco")
    @Past(message = "Coloque uma data nascimento valida!")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotNull(message = "O campo endereco não pode ficar em branco")
    @NotBlank(message = "O campo endereço é obrigatório!")
    @Column(nullable = false)
    private String endereco;

    @NotNull(message = "O campo CPF não pode ficar em branco")
    @NotBlank(message = "O campo CPF é obrigatório!")
    @Max(value = 11)
    @Column(nullable = false, unique = false, length = 11)
    private String cpf;

    @NotNull(message = "O campo salario não pode ficar em branco")
    @Positive(message = "O campo salário deve ser maior que zero!")
    @Column(nullable = false)
    private Double salario;

    @NotNull(message = "O campo data de admissão não pode ficar em branco")
    @NotBlank(message = "O campo data de admissão é obrigatório!")
    @PastOrPresent(message = "O campo deve esta com a data atual!")
    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    @NotNull(message = "O campo número da carteira de trabalho não pode ficar em branco")
    @NotBlank(message = "O campo número da carteira de trabalho é obrigatório!")
    @Column(name = "numero_carteira_trabalho", nullable = false, unique = false)
    private String numeroCarteiraTrabalho;

    @NotNull(message = "O campo cargo não pode ficar em branco")
    @NotBlank(message = "O campo cargo é obrigatório!")
    @Column(nullable = false)
    private String cargo;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getNumeroCarteiraTrabalho() {
        return numeroCarteiraTrabalho;
    }

    public void setNumeroCarteiraTrabalho(String numeroCarteiraTrabalho) {
        this.numeroCarteiraTrabalho = numeroCarteiraTrabalho;
    }

}
