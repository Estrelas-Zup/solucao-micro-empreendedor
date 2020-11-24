package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import br.com.zup.estrelas.sme.dto.DespesaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Despesa;

public interface DespesaServiceImp {
    
    public MensagemDTO adicionarDespesa(DespesaDTO despesa);
    
    public List<Despesa> listarDespesas();
    
    public MensagemDTO removerDespesa(Long idDespesa);
}
