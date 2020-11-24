package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.AdicionarVendaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Venda;

public interface VendaService {
    public MensagemDTO adicionarVenda(AdicionarVendaDTO vendaDTO);
    
    public MensagemDTO alterarVenda(Long numeroVenda, AdicionarVendaDTO vendaDTO);
    
    public Venda buscarVendaPorId(Long numeroPedido);
    
    public List<Venda> listarVendas();
    
    public MensagemDTO removerVenda(Long numeroPedido);
}
