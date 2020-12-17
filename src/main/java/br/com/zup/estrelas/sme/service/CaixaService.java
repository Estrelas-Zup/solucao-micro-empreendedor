package br.com.zup.estrelas.sme.service;

import java.time.LocalDate;
import java.util.List;
import br.com.zup.estrelas.sme.dto.CaixaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Caixa;
import br.com.zup.estrelas.sme.exceptions.GenericException;

public interface CaixaService {

    public MensagemDTO adicionarCaixa(CaixaDTO caixaDTO);

    public List<Caixa> listarCaixa();

    public Caixa consultarCaixaPorData(LocalDate data) throws GenericException;

    public Caixa consultarPorId(Long idCaixa) throws GenericException;

    public MensagemDTO fechamentoCaixa(Long idCaixa);

}
