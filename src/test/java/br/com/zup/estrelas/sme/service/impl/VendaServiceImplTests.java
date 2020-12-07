package br.com.zup.estrelas.sme.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.sme.repository.CaixaRepository;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.RelatorioVendaRepository;
import br.com.zup.estrelas.sme.repository.VendaRepository;

@RunWith(MockitoJUnitRunner.class)
public class VendaServiceImplTests {

    @Mock
    VendaRepository repository;

    @Mock
    EstoqueRepository estoqueRepository;

    @Mock
    RelatorioVendaRepository relatorioVendaRepository;

    @Mock
    CaixaRepository caixaRepository;

    @InjectMocks
    VendaServiceImpl vendaService;

    @Test
    public void deveRealizarVendaComSucesso() {

    }

    @Test
    public void naoDeveRealizarVendaDeProdutoInexistente() {

    }

    @Test
    public void naoDeveRealizarVendaSeCaixaNaoEstiverAberto() {

    }

    @Test
    public void deveAlterarVendaComSucesso() {

    }

    @Test
    public void naoDeveAlterarVendaInexistente() {

    }

    @Test
    public void naoDeveAlterarVendaCasoValorDescontoSupeiorAoValorTotal() {

    }

    @Test
    public void deveRemoverVendaComSucesso() {

    }

    @Test
    public void naoDeveRemoverVendaInexistente() {

    }
}
