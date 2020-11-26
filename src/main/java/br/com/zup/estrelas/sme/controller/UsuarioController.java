package br.com.zup.estrelas.sme.controller;

import java.util.List;
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
    public MensagemDTO adicionaUsuario(@RequestBody UsuarioDTO adicionaUsuarioDto) {

        return usuarioService.adicionaUsuario(adicionaUsuarioDto);
    }

    @PutMapping(path = "/{idEmail}", produces = {MediaType.APPLICATION_JSON_VALUE})

    public MensagemDTO alteraUsuario(@PathVariable String idEmail,
            @RequestBody UsuarioDTO alteraUsuarioD) {

        return usuarioService.alterarUsuario(idEmail, alteraUsuarioD);
    }

    @GetMapping(path = "/{idEmail}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Usuario consultaUsuario(@PathVariable String idEmail) {

        return usuarioService.consultaUsuario(idEmail);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})

    public List<Usuario> listarUsario() {
        return usuarioService.listarUsuarios();

    }

    @DeleteMapping(path = "/{idEmail}", produces = {MediaType.APPLICATION_JSON_VALUE})

    public MensagemDTO removerUsuario(@PathVariable String idEmail) {


        return usuarioService.removerUsuario(idEmail);

    }

}
