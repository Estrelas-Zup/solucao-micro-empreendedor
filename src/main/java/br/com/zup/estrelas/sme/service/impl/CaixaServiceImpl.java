package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.CaixaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Caixa;
import br.com.zup.estrelas.sme.entity.Estoque;
import br.com.zup.estrelas.sme.entity.Gestao;
import br.com.zup.estrelas.sme.exceptions.GenericException;
import br.com.zup.estrelas.sme.repository.CaixaRepository;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.GestaoRepository;
import br.com.zup.estrelas.sme.service.CaixaService;

@Service
public class CaixaServiceImpl implements CaixaService {

    private static final String PRODUTOS_COM_A_DATA_DE_VALIDADE_VENCIDO =
            "Produtos com a data de validade vencido!";
    private static final String CAIXA_FECHADO_COM_SUCESSO = "Caixa fechado com sucesso!";
    private static final String CAIXA_JÁ_ESTA_FECHADO = "Caixa já se encontra fechado!";
    private static final String CAIXA_EXISTENTE =
            "Infelizmente não foi possivel realizar operação, caixa já existente com a data atual";
    private static final String SALDO_INICIAL_MAIOR_QUE_CAPITAL_INICIAL =
            "Infelizmente não foi possivel realizar operação, saldo inicial maior que capital inicial";
    private static final String GESTÃO_INEXISTENTE =
            "Infelizmente não foi possivel realizar a operação, gestão inexistente!";
    private static final String CAIXA_CADASTRADO_COM_SUCESSO = "Caixa cadastrado com sucesso!";
    private static final String CAIXA_INEXISTENTE =
            "Não foi possivel realizar a operação, caixa inexistente.";

    @Autowired
    CaixaRepository caixaRepository;

    @Autowired
    GestaoRepository gestaoRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    public MensagemDTO adicionarCaixa(CaixaDTO caixaDTO) {
        Optional<Gestao> gestaoConsultado = buscarGestao();

        if (gestaoConsultado.isEmpty()) {
            return new MensagemDTO(GESTÃO_INEXISTENTE);
        }

        Gestao gestao = gestaoConsultado.get();
        boolean verificaSaldoCapital = gestao.getCapitalSocial() < caixaDTO.getSaldoInicial();

        if (verificaSaldoCapital) {
            return new MensagemDTO(SALDO_INICIAL_MAIOR_QUE_CAPITAL_INICIAL);
        }

        if (caixaRepository.existsByData(LocalDate.now())) {
            return new MensagemDTO(CAIXA_EXISTENTE);
        }

        gestaoRepository.save(novoSaldoCapitalSocial(caixaDTO, gestao));

        caixaRepository.save(copiaDadosDTOParaCaixa(caixaDTO));

        return new MensagemDTO(CAIXA_CADASTRADO_COM_SUCESSO);
    }

    public List<Caixa> listarCaixa() {
        return (List<Caixa>) caixaRepository.findAll();
    }

    public Caixa consultarPorId(Long idCaixa) throws GenericException {
        return caixaRepository.findById(idCaixa).orElseThrow(() -> new GenericException(CAIXA_INEXISTENTE));
    }

    public Caixa consultarCaixaPorData(LocalDate data) throws GenericException {
        return caixaRepository.findByData(data)
                .orElseThrow(() -> new GenericException(CAIXA_INEXISTENTE));
    }

    public MensagemDTO fechamentoCaixa(Long idCaixa) {
        Optional<Caixa> caixaConsultado = caixaRepository.findById(idCaixa);

        if (caixaConsultado.isEmpty()) {
            return new MensagemDTO(CAIXA_INEXISTENTE);
        }

        Caixa caixa = caixaConsultado.get();

        if (!caixa.isCaixaAberto()) {
            return new MensagemDTO(CAIXA_JÁ_ESTA_FECHADO);
        }

        double somaSaldoInicialEValorTotalCaixa = caixa.getSaldoInicial() + caixa.getValorTotal();
        caixa.setCaixaAberto(false);
        caixaRepository.save(caixa);

        Gestao gestao = buscarGestao().get();
        double novoCapitalSocial = gestao.getCapitalSocial() + somaSaldoInicialEValorTotalCaixa;
        gestao.setCapitalSocial(novoCapitalSocial);
        gestaoRepository.save(gestao);

        List<Estoque> estoquesConsultados = (List<Estoque>) estoqueRepository.findAll();

        verificaDataValidadeEmEstoque(estoquesConsultados);

        return new MensagemDTO(CAIXA_FECHADO_COM_SUCESSO);
    }

    private void verificaDataValidadeEmEstoque(List<Estoque> verificaValiadeEmEstoques) {
        for (Estoque estoque : verificaValiadeEmEstoques) {
            if (estoque.getDataValidade().equals(LocalDate.now())) {
                estoque.setPerda(true);
                estoque.setMotivoPerda(PRODUTOS_COM_A_DATA_DE_VALIDADE_VENCIDO);
                estoque.setDisponibilidade(false);

                estoqueRepository.save(estoque);
            }
        }
    }

    private Optional<Gestao> buscarGestao() {
        List<Gestao> listaGestao = (List<Gestao>) gestaoRepository.findAll();

        return listaGestao.stream().findFirst();
    }

    private Caixa copiaDadosDTOParaCaixa(CaixaDTO caixaDTO) {
        Caixa caixa = new Caixa();
        BeanUtils.copyProperties(caixaDTO, caixa);
        caixa.setData(LocalDate.now());
        caixa.setCaixaAberto(true);
        return caixa;
    }

    private Gestao novoSaldoCapitalSocial(CaixaDTO caixaDTO, Gestao gestao) {
        double novoSaldoCapitalSocial = gestao.getCapitalSocial() - caixaDTO.getSaldoInicial();
        gestao.setCapitalSocial(novoSaldoCapitalSocial);
        return gestao;
    }

}
