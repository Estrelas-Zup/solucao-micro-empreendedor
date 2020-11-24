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

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private static final String FUNCIONARIO_CADASTRADO_COM_SUCESSO = "funcionario cadastrado com Sucesso.";
    private static final String FUNCIONARIO_ALTERADO_COM_SUCESSO = "funcionario alterado com Sucesso.";
    private static final String FUNCIONARIO_NAO_ALTERADO = "funcionario não pode ser alterado.";
    private static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO = "funcionario removida com Sucesso.";
    private static final String FUNCIONARIO_JA_CADASTRADO ="O cadastro não ocorreu, funcionario já cadastrado na Prefeitura!";
    private static final String FUNCIONARIO_INEXISTENTE = "A funcionario não existe.";
    private static final String FUNCIONARIO_NAO_CADASTRADO = "O funcionario não foi cadastrado";


    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    public MensagemDTO adicionarFuncionario(AdicionarFuncionarioDTO adicionarFuncionarioDTO) {

        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(adicionarFuncionarioDTO, funcionario);

        if (funcionario.getCpf() != null) {
            return new MensagemDTO(FUNCIONARIO_JA_CADASTRADO);
        }

        funcionario.setDataAdmissao(LocalDate.now());
        funcionarioRepository.save(funcionario);
        return new MensagemDTO(FUNCIONARIO_CADASTRADO_COM_SUCESSO);

    }

    @Override
    public MensagemDTO alterarFuncionario(Long idFuncionario, AlteraFuncionarioDTO alteraFuncionarioDTO) {
           
        Optional<Funcionario> funcionarioConsultado = funcionarioRepository.findById(idFuncionario);
        
        
        if (funcionarioConsultado.isEmpty()) {
          return new MensagemDTO(FUNCIONARIO_NAO_ALTERADO);
        }
        
        Funcionario funcionario = funcionarioConsultado.get();   
        BeanUtils.copyProperties(alteraFuncionarioDTO, funcionario);
        funcionarioRepository.save(funcionario);
        
        return new MensagemDTO(FUNCIONARIO_ALTERADO_COM_SUCESSO);
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

        if (funcionarioRepository.existsByCpf(cpf)) {
            funcionarioRepository.deleteByCpf(cpf);
            return new MensagemDTO(FUNCIONARIO_REMOVIDO_COM_SUCESSO);
        }

        return new MensagemDTO(FUNCIONARIO_INEXISTENTE);
    }

    @Override
    public MensagemDTO verificarDisponibilidadeContratacao(Double salario) {
        // TODO Auto-generated method stub
        return null;
    }

   
}