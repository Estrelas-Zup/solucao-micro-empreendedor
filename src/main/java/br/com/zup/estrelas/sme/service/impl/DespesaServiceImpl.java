package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.DespesaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Caixa;
import br.com.zup.estrelas.sme.entity.Despesa;
import br.com.zup.estrelas.sme.entity.Gestao;
import br.com.zup.estrelas.sme.repository.CaixaRepository;
import br.com.zup.estrelas.sme.repository.DespesaRepository;
import br.com.zup.estrelas.sme.repository.GestaoRepository;
import br.com.zup.estrelas.sme.service.DespesaService;

@Service
public class DespesaServiceImpl implements DespesaService {

    private static final String NÃO_HOUVE_ABERTURA_DE_COMERCIO =
            "Infelizmente não foi possivel realizar a operação, não houve abertura de comercio.";
    private static final String VALOR_DA_DESPESA_SUPERIOR_AO_CAPITAL_SOCIAL =
            "Infelizmente não foi possivel realizar a operação, valor da despesa superior ao capital social, busque investimentos para seu négocio";
    private static final String ABERTURA_DE_CAIXA_NAO_REALIZADA =
            "Infelizmente não foi possivel realizar a operação, ainda não houve abertura de caixa no dia de hoje.";
    private static final String DESPESA_CADASTRADA_COM_SUCESSO = "Despesa cadastrada com sucesso!";
    private static final String DESPESA_INEXISTENTE =
            "Não foi possivel realizar a operação, despesa inexistente.";
    private static final String DESPESA_REMOVIDA_COM_SUCESSO = "Despesa removida com sucesso!";
    private static final String DESPESA_ALTERADA_COM_SUCESSO = "Despesa alterada com sucesso!";

    @Autowired
    DespesaRepository despesaRepository;

    @Autowired
    CaixaRepository caixaRepository;

    @Autowired
    GestaoRepository gestaoRepository;

    // TODO: Refatorar verificação de Gestao e Caixa
    public MensagemDTO adicionarDespesa(DespesaDTO despesaDTO) {
        Optional<Gestao> gestaoConsultada = buscarGestao();

        if (gestaoConsultada.isEmpty()) {
            return new MensagemDTO(NÃO_HOUVE_ABERTURA_DE_COMERCIO);
        }

        Gestao gestao = gestaoConsultada.get();

        Optional<Caixa> caixaConsultado = caixaRepository.findByData(LocalDate.now());

        if (caixaConsultado.isEmpty()) {
            return new MensagemDTO(ABERTURA_DE_CAIXA_NAO_REALIZADA);
        }

        Caixa caixa = caixaConsultado.get();

        if (verificarDisponibilidadeCapitalSocial(despesaDTO, gestao)) {
            adicionarNovoValorCapitalSocialGestao(despesaDTO, gestao);
        } else {
            return new MensagemDTO(VALOR_DA_DESPESA_SUPERIOR_AO_CAPITAL_SOCIAL);
        }

        adicionarValorDepesaEmCaixa(caixa, despesaDTO.getValor());

        Despesa despesa = new Despesa();
        BeanUtils.copyProperties(despesaDTO, despesa);

        despesa.setCaixa(caixa);
        despesaRepository.save(despesa);
        return new MensagemDTO(DESPESA_CADASTRADA_COM_SUCESSO);
    }

    public MensagemDTO alterarDespesa(Long idDespesa, DespesaDTO despesaDTO) {
        Optional<Despesa> despesaConsultada = despesaRepository.findById(idDespesa);

        if (despesaConsultada.isEmpty()) {
            return new MensagemDTO(DESPESA_INEXISTENTE);
        }

        Despesa despesa = despesaConsultada.get();
        Gestao gestao = buscarGestao().get();

        Long idCaixa = despesa.getCaixa().getIdCaixa();

        Double valorAtualDespesa = despesa.getValor();
        Double novoValorDespesa = despesaDTO.getValor();

        boolean valorTotalDespesaDiferenteAtual = valorAtualDespesa != novoValorDespesa;

        if (valorTotalDespesaDiferenteAtual) {
            gestao.setCapitalSocial(gestao.getCapitalSocial() + valorAtualDespesa);

            if (verificarDisponibilidadeCapitalSocial(despesaDTO, gestao)) {
                creditarCapitalSocialGestao(valorAtualDespesa);
                subtrairValorTotalDespesaCaixa(valorAtualDespesa, idCaixa);

                subtrairCapitalSocialGestao(novoValorDespesa);
                creditarValorTotalDepesaCaixa(novoValorDespesa, idCaixa);
            } else {
                return new MensagemDTO(VALOR_DA_DESPESA_SUPERIOR_AO_CAPITAL_SOCIAL);
            }
        }

        BeanUtils.copyProperties(despesaDTO, despesa);

        despesaRepository.save(despesa);

        return new MensagemDTO(DESPESA_ALTERADA_COM_SUCESSO);

    }

    public List<Despesa> listarDespesa() {
        return (List<Despesa>) despesaRepository.findAll();
    }

    public MensagemDTO removerDespesa(Long idDespesa) {
        if (despesaRepository.existsById(idDespesa)) {
            Despesa despesa = despesaRepository.findById(idDespesa).get();

            Double valorDespesa = despesa.getValor();
            Long idCaixa = despesa.getCaixa().getIdCaixa();

            creditarCapitalSocialGestao(valorDespesa);
            subtrairValorTotalDespesaCaixa(valorDespesa, idCaixa);

            despesaRepository.deleteById(idDespesa);
            return new MensagemDTO(DESPESA_REMOVIDA_COM_SUCESSO);
        }

        return new MensagemDTO(DESPESA_INEXISTENTE);
    }

    public boolean verificarDisponibilidadeCapitalSocial(DespesaDTO despesaDTO, Gestao gestao) {
        boolean verificarValorDespesaMaiorCapitalSocial =
                despesaDTO.getValor() > gestao.getCapitalSocial();

        if (verificarValorDespesaMaiorCapitalSocial) {
            return false;
        }

        return true;
    }

    public void adicionarNovoValorCapitalSocialGestao(DespesaDTO despesaDTO, Gestao gestao) {
        Double novoValorCapitalSocial = gestao.getCapitalSocial() - despesaDTO.getValor();
        gestao.setCapitalSocial(novoValorCapitalSocial);

        gestaoRepository.save(gestao);
    }

    public void adicionarValorDepesaEmCaixa(Caixa caixa, Double valorDespesa) {
        caixa.setValorTotalDespesa(caixa.getValorTotalDespesa() + valorDespesa);

        caixaRepository.save(caixa);
    }

    public void creditarCapitalSocialGestao(Double valorDespesa) {
        Gestao gestaoConsultada = buscarGestao().get();

        gestaoConsultada.setCapitalSocial(gestaoConsultada.getCapitalSocial() + valorDespesa);

        gestaoRepository.save(gestaoConsultada);
    }

    public void creditarValorTotalDepesaCaixa(Double valorDespesa, Long idCaixa) {
        Caixa caixa = caixaRepository.findById(idCaixa).get();

        caixa.setValorTotalDespesa(caixa.getValorTotalDespesa() + valorDespesa);

        caixaRepository.save(caixa);
    }

    public void subtrairCapitalSocialGestao(Double valorDespesa) {
        Gestao gestaoConsultada = buscarGestao().get();

        gestaoConsultada.setCapitalSocial(gestaoConsultada.getCapitalSocial() - valorDespesa);

        gestaoRepository.save(gestaoConsultada);
    }

    public void subtrairValorTotalDespesaCaixa(Double valorDespesa, Long idCaixa) {
        Caixa caixa = caixaRepository.findById(idCaixa).get();

        caixa.setValorTotalDespesa(caixa.getValorTotalDespesa() - valorDespesa);

        caixaRepository.save(caixa);
    }

    public Optional<Gestao> buscarGestao() {
        List<Gestao> listaGestao = (List<Gestao>) gestaoRepository.findAll();

        return listaGestao.stream().findFirst();
    }

}
