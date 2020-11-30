package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.RemoveUsuarioDTO;
import br.com.zup.estrelas.sme.dto.UsuarioDTO;
import br.com.zup.estrelas.sme.entity.Usuario;
import br.com.zup.estrelas.sme.repository.UsuarioRepository;
import br.com.zup.estrelas.sme.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final String USUARIO_EXCLUIDO_COM_SUCESSO = "Usuario excluido com sucesso!";

    private static final String USUARIO_INEXISTENTE =
            "Não foi possivel realizar a alteração, usuario inexistente.";

    private static final String USUARIO_ALTERADO_COM_SUCESSO = "Usuario alterado com sucesso!";

    private static final String USUARIO_CADASTRADO_COM_SUCESSO = "Usuario cadastrado com sucesso!";

    @Autowired
    UsuarioRepository usuarioRepository;

    public MensagemDTO adicionarUsuario(UsuarioDTO adicionaUsuarioDTO) {
        
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(adicionaUsuarioDTO, usuario);

        usuarioRepository.save(usuario);

        return new MensagemDTO(USUARIO_CADASTRADO_COM_SUCESSO);
    }

    //TODO: Alterar UsuarioDTO para AlteraUsuarioDTO (senha, role)
    public MensagemDTO alterarUsuario(UsuarioDTO alterarUsuarioDTO) {

        Optional<Usuario> usuarioConsultado = usuarioRepository.findById(alterarUsuarioDTO.getEmail());

        if (usuarioConsultado.isEmpty()) {
            return new MensagemDTO(USUARIO_INEXISTENTE);
        }

        Usuario usuario = usuarioConsultado.get();

        BeanUtils.copyProperties(alterarUsuarioDTO, usuario);

        usuarioRepository.save(usuario);
        return new MensagemDTO(USUARIO_ALTERADO_COM_SUCESSO);
    }

    public Usuario consultarUsuarioPorEmail(String email) {
        return usuarioRepository.findById(email).orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public MensagemDTO removerUsuario(RemoveUsuarioDTO removeUsuarioDTO) {
        
        if (usuarioRepository.existsById(removeUsuarioDTO.getEmail())) {
            usuarioRepository.deleteById(removeUsuarioDTO.getEmail());
            return new MensagemDTO(USUARIO_EXCLUIDO_COM_SUCESSO);
        }

        return new MensagemDTO(USUARIO_INEXISTENTE);
    }

}
