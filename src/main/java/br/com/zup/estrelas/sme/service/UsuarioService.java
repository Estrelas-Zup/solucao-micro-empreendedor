package br.com.zup.estrelas.sme.service;


import java.util.List;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.UsuarioDTO;
import br.com.zup.estrelas.sme.entity.Usuario;

public interface UsuarioService {

    public MensagemDTO adicionaUsuario(UsuarioDTO adicionaUsuarioDto);

    public MensagemDTO alterarUsuario(String email, UsuarioDTO alteraUsuarioD);

    public Usuario consultaUsuario(String email);

    public MensagemDTO removerUsuario(String email);

    public List<Usuario> listarUsuarios();

}
