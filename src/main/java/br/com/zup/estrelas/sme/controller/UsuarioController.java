package br.com.zup.estrelas.sme.controller;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.sme.dto.AlterarUsuarioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.UsuarioDTO;
import br.com.zup.estrelas.sme.entity.Usuario;
import br.com.zup.estrelas.sme.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.adicionarUsuario(usuarioDTO);
    }
    
    // TODO: Passar email no corpo e não na URL
    @PutMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public MensagemDTO alterarUsuario(@PathVariable String email,
            @RequestBody AlterarUsuarioDTO alterarUsuarioDTO) {

        return usuarioService.alterarUsuario(email, alterarUsuarioDTO);
    }

    @GetMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Usuario consultarUsuarioPorEmail(@PathVariable String email) {

        return usuarioService.consultarUsuarioPorEmail(email);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Usuario> listarUsario() {
        return usuarioService.listarUsuarios();

    }

    @DeleteMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public MensagemDTO removerUsuario(@PathVariable String email) {
        return usuarioService.removerUsuario(email);
    }

}
