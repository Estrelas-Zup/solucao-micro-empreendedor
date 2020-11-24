package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.DespesaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Despesa;

public interface DespesaService {
    
    public MensagemDTO adicionarDespesa(DespesaDTO despesa);
    
    public List<Despesa> listarDespesa();
    
    public MensagemDTO alterarDespesa(Long idDespesa, DespesaDTO despesaDTO);
    
    public MensagemDTO removerDespesa(Long idDespesa);
    
}
