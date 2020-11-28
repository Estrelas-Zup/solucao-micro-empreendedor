package br.com.zup.estrelas.sme.service;

import java.util.List;
import br.com.zup.estrelas.sme.dto.ConsultaDataDTO;
import br.com.zup.estrelas.sme.dto.CaixaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Caixa;

public interface CaixaService {

    public MensagemDTO adicionarCaixa(CaixaDTO caixaDTO);

    public List<Caixa> listarCaixa();

    public Caixa consultarCaixaPorData(ConsultaDataDTO consultaDataDTO);

    public Caixa consultarPorId(Long idCaixa);

    public MensagemDTO alterarCaixa(Long idCaixa, CaixaDTO caixaDTO);

    public MensagemDTO removerCaixa(Long idCaixa);

}
