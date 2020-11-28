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
import br.com.zup.estrelas.sme.dto.AdicionarFuncionarioDTO;
import br.com.zup.estrelas.sme.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Funcionario;
import br.com.zup.estrelas.sme.service.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/funcionarios")
@Api(value = "Funcionário", description = "REST API Funcionário", tags = { "Funcionário" })
public class FuncionarioController {
    
    @Autowired
    FuncionarioService funcionarioService;
    
    @PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Adicionar funcionário")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Criado com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno no servidor.")})
    public MensagemDTO adicionarFuncionario(@RequestBody AdicionarFuncionarioDTO adicionarFuncionarioDTO) {
        return funcionarioService.adicionarFuncionario(adicionarFuncionarioDTO);
    }
    
    @PutMapping(path = "/{idFuncionario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Alterar funcionário")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Alteração do funcionário feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum funcionário alterado.")})
    public MensagemDTO alterarFuncionario(@PathVariable Long idFuncionario, @RequestBody AlteraFuncionarioDTO alteraFuncionarioDTO) {
        return funcionarioService.alterarFuncionario(idFuncionario, alteraFuncionarioDTO); 
    }
    
    @GetMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Consultar funcionário por CPF")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Consulta do funcionário pelo CPF realizada com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum funcionário encontrado pelo CPF.")})
    public Funcionario consultarFuncionarioPorCpf(@PathVariable String cpf) {
        return funcionarioService.consultarFuncionarioPorCpf(cpf);
    }
    
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Listar funcionário")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Listagem de funcionários realizada com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum funcionário encontrado!")})
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }
    
    @DeleteMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Remover funcionário")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Remoção do funcionário feita com sucesso!"),
                    @ApiResponse(code = 204, message = "Nenhum funcionário removido!")})
    @Transactional
    public MensagemDTO removerFuncionario(@PathVariable String cpf) {
        return funcionarioService.removerFuncionario(cpf);
    }
    
}
