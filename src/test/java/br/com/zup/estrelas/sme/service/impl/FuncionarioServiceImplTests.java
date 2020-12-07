package br.com.zup.estrelas.sme.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.sme.repository.FuncionarioRepository;
import br.com.zup.estrelas.sme.service.GestaoService;

@RunWith(MockitoJUnitRunner.class)
public class FuncionarioServiceImplTests {

    @Mock
    FuncionarioRepository funcionarioRepository;

    @Mock
    GestaoService gestaoService;

    @InjectMocks
    FuncionarioServiceImpl funcionarioService;

    @Test
    public void deveAdionarFuncionarioComSucesso() {

    }

    @Test
    public void naoDeveAdionarFuncionarioJaExistente() {

    }

    @Test
    public void deveAlterarFuncionarioComSucesso() {

    }

    @Test
    public void naoDeveAlterarFuncionarioInexistente() {

    }

    @Test
    public void naoDeveAlterarFuncionarioComSalarioMenorQueAtual() {

    }

    @Test
    public void naoDeveAlterarFuncionarioComSalarioMaiorDoQueLucroMensal() {

    }
}
