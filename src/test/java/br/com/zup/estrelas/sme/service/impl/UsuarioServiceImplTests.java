package br.com.zup.estrelas.sme.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.sme.repository.UsuarioRepository;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceImplTests {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    UsuarioServiceImpl usuarioService;

    @Test
    public void deveAdicionarUsuarioComSucesso() {

    }

    @Test
    public void naoDeveAdicionarUsuarioJaExistente() {

    }

    @Test
    public void deveAlterarUsuarioComSucesso() {

    }

    @Test
    public void naoDeveAlterarUsuarioInexistente() {

    }

    @Test
    public void deveRemoverUsuarioComSucesso() {

    }

    @Test
    public void naoDeveRemoverUsuarioInexistente() {

    }
}
