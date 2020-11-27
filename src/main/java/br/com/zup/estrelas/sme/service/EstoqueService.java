package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.ContabilizaPerdaDTO;
import br.com.zup.estrelas.sme.dto.EstoqueDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Estoque;

public interface EstoqueService {

    public MensagemDTO adicionarEstoque(EstoqueDTO estoqueDTO);

    public MensagemDTO alterarEstoque(Long idEstorque, EstoqueDTO estoqueDTO);

    public List<Estoque> consultarEstoquePorProduto(Long idProduto);

    public List<Estoque> listarEstoques();
    
    public MensagemDTO contablizarPerda(Long idEstoque, ContabilizaPerdaDTO contabilizaPerdaDTO);
}
