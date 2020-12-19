package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.AdicionarVendaDTO;
import br.com.zup.estrelas.sme.dto.AlterarVendaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Venda;
import br.com.zup.estrelas.sme.exceptions.GenericException;

public interface VendaService {
    public MensagemDTO adicionarVenda(AdicionarVendaDTO adicionarVendaDTO);
    
    public MensagemDTO alterarVenda(Long idVenda, AlterarVendaDTO alterarVendaDTO);
    
    public Venda buscarVendaPorId(Long idVenda) throws GenericException;
    
    public List<Venda> listarVendas();
    
    public MensagemDTO removerVenda(Long idVenda) throws GenericException;
}
