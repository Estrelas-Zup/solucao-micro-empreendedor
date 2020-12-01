package br.com.zup.estrelas.sme.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long idFuncionario;

    @Column(nullable = false)
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String nome;

    @Column(nullable = false)
    @Email(message = "O campo email esta invalido.")
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String email;

    @Column(nullable = false)
    @Size(min = 10, max = 11,
            message = "O campo deve conter no mínimo 10 e no máximo 11 caracteres.")
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String telefone;

    @Column(name = "data_nascimento", nullable = false)
    @Past(message = "O campo deve possuir data anterior ao dia atual.")
    @NotNull(message = "O campo não pode ser vazio.")
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String endereco;

    @Column(nullable = false, unique = false, length = 11)
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    @CPF(message = "O campo CPF esta invalido.")
    private String cpf;

    @Column(nullable = false)
    @Positive(message = "O campo deve ser maior que zero.")
    @NotNull(message = "O campo não pode ser vazio.")
    private Double salario;

    @Column(name = "data_admissao", nullable = false)
    @PastOrPresent(message = "O campo deve possuir data atual ou anterior.")
    @NotNull(message = "O campo não pode ser vazio.")
    private LocalDate dataAdmissao;

    @Column(name = "numero_carteira_trabalho", nullable = false, unique = false)
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
    private String numeroCarteiraTrabalho;

    @Column(nullable = false)
    @NotBlank(message = "O campo não pode ficar vazio ou conter apenas espaços.")
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
