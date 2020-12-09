package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.UsuarioDTO;
import br.com.zup.estrelas.sme.entity.Usuario;
import br.com.zup.estrelas.sme.repository.UsuarioRepository;
import br.com.zup.estrelas.sme.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final String EMAIL_JÁ_CADASTRADO =
            "Infelizmente não foi possivel realizar a operação, email já cadastrado.";

    private static final String USUARIO_EXCLUIDO_COM_SUCESSO = "Usuario excluido com sucesso!";

    private static final String USUARIO_INEXISTENTE =
            "Não foi possivel realizar a alteração, usuario inexistente.";

    private static final String USUARIO_ALTERADO_COM_SUCESSO = "Usuario alterado com sucesso!";

    private static final String USUARIO_CADASTRADO_COM_SUCESSO = "Usuario cadastrado com sucesso!";

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;


    public MensagemDTO adicionarUsuario(UsuarioDTO adicionaUsuarioDTO) {
        if (usuarioRepository.existsByEmail(adicionaUsuarioDTO.getEmail())) {
            return new MensagemDTO(EMAIL_JÁ_CADASTRADO);
        }

        Usuario usuario = new Usuario();

        BeanUtils.copyProperties(adicionaUsuarioDTO, usuario);
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        usuarioRepository.save(usuario);

        return new MensagemDTO(USUARIO_CADASTRADO_COM_SUCESSO);
    }

    public MensagemDTO alterarUsuario(Long idUsuario, UsuarioDTO alterarUsuarioDTO) {
        //TODO: Verificar se esta alterando usuario que est logado.
        Optional<Usuario> usuarioConsultado = usuarioRepository.findById(idUsuario);

        if (usuarioConsultado.isEmpty()) {
            return new MensagemDTO(USUARIO_INEXISTENTE);
        }

        Usuario usuario = usuarioConsultado.get();

        BeanUtils.copyProperties(alterarUsuarioDTO, usuario);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        
        usuarioRepository.save(usuario);
        return new MensagemDTO(USUARIO_ALTERADO_COM_SUCESSO);
    }

    public Usuario consultarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public MensagemDTO removerUsuario(Long idUsuario) {

        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return new MensagemDTO(USUARIO_EXCLUIDO_COM_SUCESSO);
        }

        return new MensagemDTO(USUARIO_INEXISTENTE);
    }

}
