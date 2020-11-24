package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import br.com.zup.estrelas.sme.dto.DespesaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Despesa;

public interface DespesaServiceImpl {

    public MensagemDTO adicionarDespesa(DespesaDTO despesaDTO);

    public List<Despesa> listarDespesa();

    public MensagemDTO alterarDespesa(Long idDespesa, DespesaDTO despesaDTO);

    public MensagemDTO removerDespesa(Long idDespesa);


}
