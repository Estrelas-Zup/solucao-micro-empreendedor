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
import br.com.zup.estrelas.sme.repository.FuncionarioRepository;
import br.com.zup.estrelas.sme.service.FuncionarioService;
import br.com.zup.estrelas.sme.service.GestaoService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private static final String LUCRO_MENSAL_INSUFICIENTE_PARA_A_CONTRATAÇÃO =
            "Infelizmente não foi possivel realizar a operação, lucro mensal insuficiente para a contratação.";
    private static final String NOVO_SALARIO_NÃO_PODE_SER_MENOR_QUE_O_SALARIO_ATUAL =
            "Infelizmente não foi possivel realizar a alteração, o novo salario não pode ser menor que o salario atual.";
    private static final String FUNCIONARIO_CADASTRADO_COM_SUCESSO =
            "Funcionario cadastrado com sucesso!";
    private static final String FUNCIONARIO_ALTERADO_COM_SUCESSO =
            "Funcionario alterado com sucesso!";
    private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO =
            "Funcionario removido com sucesso!";
    private static final String FUNCIONARIO_INEXISTENTE = "Funcionario inexistente.";

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    GestaoService gestaoService;

    @Override
    public MensagemDTO adicionarFuncionario(AdicionarFuncionarioDTO adicionarFuncionarioDTO) {

        Funcionario funcionario = new Funcionario();
        // TODO : Deve verificar se AdicionarFuncionarioDTO é nulo dentro deste metodo!
        BeanUtils.copyProperties(adicionarFuncionarioDTO, funcionario);


        if (verificarDisponibilidadeContratacao(funcionario.getSalario())) {
            funcionarioRepository.save(funcionario);

            funcionario.setDataAdmissao(LocalDate.now());
            funcionarioRepository.save(funcionario);
            return new MensagemDTO(FUNCIONARIO_CADASTRADO_COM_SUCESSO);
        }
        //TODO: Sugiro criar uma constante (Boas praticas :D)
        return new MensagemDTO("Funcionario não cadastrado");
    }

    @Override
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

        if (verificarDisponibilidadeContratacao(funcionario.getSalario())) {
            funcionarioRepository.save(funcionario);

            return new MensagemDTO(FUNCIONARIO_ALTERADO_COM_SUCESSO);
        }

        return new MensagemDTO(LUCRO_MENSAL_INSUFICIENTE_PARA_A_CONTRATAÇÃO);

    }

    @Override
    public Funcionario consultarFuncionarioPorCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf).orElse(null);
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        return (List<Funcionario>) funcionarioRepository.findAll();
    }

    @Override
    public MensagemDTO removerFuncionario(String cpf) {
       // Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findByCpf(cpf);

        if (funcionarioRepository.existsByCpf(cpf)) {
            funcionarioRepository.deleteByCpf(cpf);;
            return new MensagemDTO(FUNCIONARIO_REMOVIDO_COM_SUCESSO);
        }

        return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
    }


    // TODO : finalizar implementação calcula lucro mensal em gestão.
    public boolean verificarDisponibilidadeContratacao(Double salario) {
        //
        // LucroDTO lucroDTO = gestaoService.calcularLucroMensal());
        //
        // if (salario <= lucroDTO.getCalcularLucroMensal()) {
        return true;
        // }
        // return false;
        //
    }


}
