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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuarios")
@Api(value = "Usuário", description = "REST API Usuário", tags = {"Usuário"})
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Adicionar usuário")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Criado com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")})
    public MensagemDTO adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.adicionarUsuario(usuarioDTO);
    }

    // TODO: Passar email no corpo e não na URL
    @PutMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Alterar usuário")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Alteração do usuário feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum usuário alterado!")})
    @Transactional
    public MensagemDTO alterarUsuario(@PathVariable String email,
            @RequestBody AlterarUsuarioDTO alterarUsuarioDTO) {

        return usuarioService.alterarUsuario(email, alterarUsuarioDTO);
    }

    @GetMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Consultar usuário por Email")
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Consulta do usuário por email realizada com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum usuário encontrado pelo Email.")})
    public Usuario consultarUsuarioPorEmail(@PathVariable String email) {

        return usuarioService.consultarUsuarioPorEmail(email);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Listar usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listagem de usuários realizada com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum usuário encontrado!")})
    public List<Usuario> listarUsuario() {
        return usuarioService.listarUsuarios();

    }

    @DeleteMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Remover usuário")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Remoção do usuário feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum usuário removido!")})
    @Transactional
    public MensagemDTO removerUsuario(@PathVariable String email) {
        return usuarioService.removerUsuario(email);
    }

}
