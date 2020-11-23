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
import br.com.zup.estrelas.sme.dto.AdicionarFuncionarioDTO;
import br.com.zup.estrelas.sme.dto.AlteraFuncionarioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Funcionario;
import br.com.zup.estrelas.sme.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    @Autowired
    FuncionarioService funcionarioService;
    
    @PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO adicionarFuncionario(@RequestBody AdicionarFuncionarioDTO adicionarFuncionarioDTO) {
        return funcionarioService.adicionarFuncionario(adicionarFuncionarioDTO);
    }
    
    @PutMapping(path = "/{idFuncionario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDTO alterarFuncionario(@PathVariable Long idFuncionario, @RequestBody AlteraFuncionarioDTO alteraFuncionarioDTO) {
        return funcionarioService.alterarFuncionario(idFuncionario, alteraFuncionarioDTO); 
    }
    
    @GetMapping(path = "/{idFuncionario}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Funcionario consultarFuncionarioPorCpf(@PathVariable String cpf) {
        return funcionarioService.consultarFuncionarioPorCpf(cpf);
    }
    
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }
    
    @DeleteMapping(path = "/{idFuncionario}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public MensagemDTO removerFuncionario(@PathVariable String cpf) {
        return funcionarioService.removerFuncionario(cpf);
    }
    
}
