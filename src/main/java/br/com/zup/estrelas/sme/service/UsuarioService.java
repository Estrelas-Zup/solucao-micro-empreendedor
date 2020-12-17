package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.UsuarioDTO;
import br.com.zup.estrelas.sme.entity.Usuario;
import br.com.zup.estrelas.sme.exceptions.GenericException;

public interface UsuarioService {

    public MensagemDTO adicionarUsuario(UsuarioDTO adicionaUsuarioDTO);

    public MensagemDTO alterarUsuario(Long idUsuario, UsuarioDTO alteraUsuarioDTO);

    public Usuario consultarUsuarioPorEmail(String email) throws GenericException;

    public MensagemDTO removerUsuario(Long idUsuario);

    public List<Usuario> listarUsuarios();

}
