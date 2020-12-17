package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.AdicionarFuncionarioDTO;
import br.com.zup.estrelas.sme.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Funcionario;
import br.com.zup.estrelas.sme.exceptions.GenericException;
import br.com.zup.estrelas.sme.repository.FuncionarioRepository;
import br.com.zup.estrelas.sme.service.FuncionarioService;
import br.com.zup.estrelas.sme.service.GestaoService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private static final String CPF_JÁ_CADASTRADO =
            "Infelizmente não foi possivel realizar a operação, CPF já cadastrado.";
    private static final String INDISPONIBILIDADE_CAIXA =
            "Infelizmente não foi possivel realizar a operação, não temos disponibilidade em caixa.";
    private static final String NOVO_SALARIO_NÃO_PODE_SER_MENOR_QUE_O_SALARIO_ATUAL =
            "Infelizmente não foi possivel realizar a alteração, o novo salario não pode ser menor que o salario atual.";
    private static final String FUNCIONARIO_INEXISTENTE =
            "Infelizmente não foi possivel realizar a alteração, funcionario inexistente.";
    private static final String FUNCIONARIO_CADASTRADO_COM_SUCESSO =
            "Funcionario cadastrado com sucesso!";
    private static final String FUNCIONARIO_ALTERADO_COM_SUCESSO =
            "Funcionario alterado com sucesso!";
    private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO =
            "Funcionario removido com sucesso!";


    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    GestaoService gestaoService;

    public MensagemDTO adicionarFuncionario(AdicionarFuncionarioDTO adicionarFuncionarioDTO) {
        if (funcionarioRepository.existsByCpf(adicionarFuncionarioDTO.getCpf())) {
            return new MensagemDTO(CPF_JÁ_CADASTRADO);
        }

        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(adicionarFuncionarioDTO, funcionario);

        if (gestaoService.verificarDisponibilidadeCapitalSocial(funcionario.getSalario())) {
            funcionario.setDataAdmissao(LocalDate.now());
            funcionarioRepository.save(funcionario);
            return new MensagemDTO(FUNCIONARIO_CADASTRADO_COM_SUCESSO);
        }

        return new MensagemDTO(INDISPONIBILIDADE_CAIXA);
    }

    public MensagemDTO alterarFuncionario(Long idFuncionario,
            AlteraFuncionarioDTO alteraFuncionarioDTO) {
        Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);

        if (funcionarioConsultado.isEmpty()) {
            return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
        }

        Funcionario funcionario = funcionarioConsultado.get();

        boolean verificaSalarioAnteriorMenorQueNovoSalario =
                funcionario.getSalario() > alteraFuncionarioDTO.getSalario();

        if (verificaSalarioAnteriorMenorQueNovoSalario) {
            return new MensagemDTO(NOVO_SALARIO_NÃO_PODE_SER_MENOR_QUE_O_SALARIO_ATUAL);
        }

        BeanUtils.copyProperties(alteraFuncionarioDTO, funcionario);

        if (gestaoService.verificarDisponibilidadeCapitalSocial(funcionario.getSalario())) {
            funcionarioRepository.save(funcionario);

            return new MensagemDTO(FUNCIONARIO_ALTERADO_COM_SUCESSO);
        }

        return new MensagemDTO(INDISPONIBILIDADE_CAIXA);

    }

    public Funcionario consultarFuncionarioPorCpf(String cpf) throws GenericException {
        return funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new GenericException(FUNCIONARIO_INEXISTENTE));
    }

    public List<Funcionario> listarFuncionarios() {
        return (List<Funcionario>) funcionarioRepository.findAll();
    }

    public MensagemDTO removerFuncionario(String cpf) {
        Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findByCpf(cpf);

        if (funcionarioConsultado.isEmpty()) {
            return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
        }

        funcionarioRepository.delete(funcionarioConsultado.get());
        return new MensagemDTO(FUNCIONARIO_REMOVIDO_COM_SUCESSO);
    }

}
