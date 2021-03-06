package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.AdicionarFuncionarioDTO;
import br.com.zup.estrelas.sme.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Funcionario;
import br.com.zup.estrelas.sme.exceptions.GenericException;

public interface FuncionarioService {

    public MensagemDTO adicionarFuncionario(AdicionarFuncionarioDTO adicionarFuncionarioDTO);

    public MensagemDTO alterarFuncionario(Long idFUncionario, AlteraFuncionarioDTO alteraFuncionarioDTO);

    public Funcionario consultarFuncionarioPorCpf(String cpf) throws GenericException;

    public List<Funcionario> listarFuncionarios();

    public MensagemDTO removerFuncionario(String cpf);
}