package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.zup.estrelas.sme.dto.AdicionarFuncionarioDTO;
import br.com.zup.estrelas.sme.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Funcionario;
import br.com.zup.estrelas.sme.repository.FuncionarioRepository;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceImplTests {
    
    @Mock
    FuncionarioRepository funcionarioRepository;
    
    @InjectMocks
    FuncionarioServiceImpl  funcionarioServiceImpl;
    
    @Test
    public void deveAdicionarUmFuncionario() {
        
        
        AdicionarFuncionarioDTO adicionaFuncionarioDTO = getUsuarioPadrao();
        
        
        //Mockito.when(funcionarioRepository.existsByCpf("123-321")).thenReturn(false);
        
        Mockito.when(funcionarioRepository.save(Mockito.any())).thenReturn(null);
    
        MensagemDTO retorno = this.funcionarioServiceImpl.adicionarFuncionario(adicionaFuncionarioDTO);
        
        MensagemDTO mensagemEsperada = new MensagemDTO("Funcionario cadastrado com sucesso!");
        
        Assert.assertEquals("Erro na mensagem esperada", mensagemEsperada, retorno);
        
        
    }
    
    @Test
    public void deveLancarExceptionQuandoFuncionarioForNulo() {
        
        
        AdicionarFuncionarioDTO adicionaFuncionarioDTO = null;
        
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            
            this.funcionarioServiceImpl.adicionarFuncionario(adicionaFuncionarioDTO);

        });
    }

    
    @Test
    public void deveAlterarUmFuncionario() {
        // Dado tal coisa 
        AlteraFuncionarioDTO alteraFuncionarioDTO = new AlteraFuncionarioDTO();
        
        alteraFuncionarioDTO.setSalario(2000.00);
        
        Funcionario funcionario = new Funcionario();
        
        funcionario.setSalario(1256.00);
        
        long idFuncionario = 1L;
        Mockito.when(funcionarioRepository.findById(idFuncionario)).thenReturn(Optional.ofNullable(funcionario));
        
        Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
        
        // quando executar 
        
        MensagemDTO retorno = this.funcionarioServiceImpl.alterarFuncionario(idFuncionario, alteraFuncionarioDTO);
        
        // então o resultado
        MensagemDTO mensagemEsperada = new MensagemDTO("Funcionario alterado com sucesso!");
        Assert.assertEquals("Erro na mensagem esperada", mensagemEsperada, retorno);
        
    }
    
    @Test
    public void deveRemoverUmFuncionario() {
        
        Mockito.when(funcionarioRepository.existsByCpf("123")).thenReturn(true);
        
        //Mockito.when(funcionarioRepository.deleteByCpf("123")).thenReturn(true);

        MensagemDTO retorno = this.funcionarioServiceImpl.removerFuncionario("123");
        MensagemDTO mensagemEsperada = new MensagemDTO("Funcionario removido com sucesso!");
        
        Assert.assertEquals("Erro na mensagem esperada",mensagemEsperada, retorno);
        
    }
    
    
    private AdicionarFuncionarioDTO getUsuarioPadrao() {
        AdicionarFuncionarioDTO adicionaFuncionarioDTO = new AdicionarFuncionarioDTO();
        
        adicionaFuncionarioDTO.setNome("Paulo");
        adicionaFuncionarioDTO.setCpf("123");
        adicionaFuncionarioDTO.setEndereco("logo ali");
        adicionaFuncionarioDTO.setDataNascimento(LocalDate.of(2020, 9, 10));
        adicionaFuncionarioDTO.setTelefone("12345-6789");
        adicionaFuncionarioDTO.setEmail("email@email.com");
        adicionaFuncionarioDTO.setCargo("TI");
        adicionaFuncionarioDTO.setSalario(1256.00);
        adicionaFuncionarioDTO.setNumeroCarteiraTrabalho("321.654");
        return adicionaFuncionarioDTO;
    }

}
