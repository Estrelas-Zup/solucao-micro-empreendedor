package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.UsuarioDTO;
import br.com.zup.estrelas.sme.entity.Usuario;
import br.com.zup.estrelas.sme.repository.UsuarioRepository;
import br.com.zup.estrelas.sme.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;


    public MensagemDTO adicionaUsuario(UsuarioDTO adicionaUsuarioDto) {

        Usuario usuario = new Usuario();

        BeanUtils.copyProperties(adicionaUsuarioDto, usuario);

        usuarioRepository.save(usuario);

        return new MensagemDTO("Usuario criado com sucesso");
    }

    public MensagemDTO alterarUsuario(String email, UsuarioDTO alteraUsuarioD) {

        Optional<Usuario> usuarioConsultado = usuarioRepository.findById(email);

        if (usuarioConsultado.isEmpty()) {


            return new MensagemDTO("Usuario não encontrado");
        }

        Usuario usuario = usuarioConsultado.get();

        BeanUtils.copyProperties(alteraUsuarioD, usuario);

        usuarioRepository.save(usuario);
        return new MensagemDTO("Alterado com sucesso");
    }

    public Usuario consultaUsuario(String email) {

        Optional<Usuario> usuarioConsultado = usuarioRepository.findById(email);

        if (usuarioConsultado.isEmpty()) {

            return new Usuario();
        }

        Usuario usuario = usuarioConsultado.get();

        return usuario;
    }


    public List<Usuario> listarUsuarios() {

        List<Usuario> usuariosConsultados = usuarioRepository.findAll();

        return usuariosConsultados;
    }

    public MensagemDTO removerUsuario(String email) {

        if (usuarioRepository.existsById(email)) {
            usuarioRepository.deleteById(email);

            return new MensagemDTO("Excluido com sucesso");

        }

        return new MensagemDTO("Usuario não encontrado");
    }

}
