package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.ConsultaDataDTO;
import br.com.zup.estrelas.sme.dto.CaixaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Caixa;

public interface CaixaService {

    public MensagemDTO adicionarCaixa(CaixaDTO caixaDTO);

    public List<Caixa> listarCaixa();

    public List<Caixa> consultarPelaData(ConsultaDataDTO dataDTO);

    public Caixa consultarPorId(Long idCaixa);

    public MensagemDTO alteraCaixa(Long idCaixa, CaixaDTO caixaDTO);

    public MensagemDTO removerCaixa(Long idCaixa);

}
