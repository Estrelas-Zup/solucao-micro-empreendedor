package br.com.zup.estrelas.sme.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.sme.repository.CaixaRepository;
import br.com.zup.estrelas.sme.repository.DespesaRepository;
import br.com.zup.estrelas.sme.repository.GestaoRepository;

@RunWith(MockitoJUnitRunner.class)
public class DespesaServiceImplTests {

    @Mock
    DespesaRepository despesaRepository;

    @Mock
    CaixaRepository caixaRepository;

    @Mock
    GestaoRepository gestaoRepository;

    @InjectMocks
    DespesaServiceImpl despesaService;

    @Test
    public void deveAdicionarDespesaComSucesso() {

    }

    @Test
    public void naoDeveAdicionarDespesaEmCaixaInexistente() {

    }

    @Test
    public void naoDeveAdicionarDespesaEmComercioInexistente() {

    }

    @Test
    public void naoDeveAdicionarDespesaSeValorForaMaiorCapitalSocial() {

    }

    @Test
    public void deveAlterarDespesaComSucesso() {

    }

    @Test
    public void naoDeveAlterarDespesaInexistente() {

    }

    @Test
    public void naoDeveAlterarDespesaSeValorForaMaiorCapitalSocial() {

    }

}
