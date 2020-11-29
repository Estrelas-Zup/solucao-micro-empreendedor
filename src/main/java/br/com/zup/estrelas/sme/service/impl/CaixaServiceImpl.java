package br.com.zup.estrelas.sme.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.ConsultaDataDTO;
import br.com.zup.estrelas.sme.dto.DespesaDTO;
import br.com.zup.estrelas.sme.dto.CaixaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Caixa;
import br.com.zup.estrelas.sme.entity.Estoque;
import br.com.zup.estrelas.sme.entity.Gestao;
import br.com.zup.estrelas.sme.repository.CaixaRepository;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.GestaoRepository;
import br.com.zup.estrelas.sme.service.CaixaService;
import br.com.zup.estrelas.sme.service.DespesaService;

@Service
public class CaixaServiceImpl implements CaixaService {

    private static final String CAIXA_CADASTRADO_COM_SUCESSO = "Caixa cadastrado com sucesso!";
    private static final String CAIXA_INEXISTENTE =
            "Não foi possivel realizar a operação, caixa inexistente.";
    private static final String CAIXA_REMOVIDO_COM_SUCESSO = "Caixa removido com sucesso!";
    private static final String CAIXA_ALTERADO_COM_SUCESSO = "Caixa alterado com sucesso!";

    @Autowired
    CaixaRepository caixaRepository;

    @Autowired
    GestaoRepository gestaoRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    DespesaService despesaService;

    public MensagemDTO adicionarCaixa(CaixaDTO caixaDTO) {
        Optional<Gestao> gestaoConsultado = buscarGestao();

        if (gestaoConsultado.isEmpty()) {
            return new MensagemDTO(
                    "Infelizmente não foi possivel realizar a operação, gestão inexistente!");
        }

        Gestao gestao = gestaoConsultado.get();
        boolean verificaSaldoCapital = gestao.getCapitalSocial() < caixaDTO.getSaldoInicial();

        if (verificaSaldoCapital) {
            return new MensagemDTO(
                    "Infelizmente não foi possivel realizar opreação, saldo inicial maior que capital inicial");
        }

        if (caixaRepository.existsByData(LocalDate.now())) {
            return new MensagemDTO(
                    "Infelizmente não foi possivel realizar operação, caixa já existente com a data atual");
        }

        double novoSaldoCapitalSocial = gestao.getCapitalSocial() - caixaDTO.getSaldoInicial();
        gestao.setCapitalSocial(novoSaldoCapitalSocial);
        gestaoRepository.save(gestao);

        Caixa caixa = new Caixa();
        BeanUtils.copyProperties(caixaDTO, caixa);
        caixa.setData(LocalDate.now());
        caixa.setCaixaAberto(true);

        caixaRepository.save(caixa);
        return new MensagemDTO(CAIXA_CADASTRADO_COM_SUCESSO);
    }

    public List<Caixa> listarCaixa() {
        return (List<Caixa>) caixaRepository.findAll();
    }

    public Caixa consultarPorId(Long idCaixa) {
        return caixaRepository.findById(idCaixa).orElse(null);
    }

    public Caixa consultarCaixaPorData(ConsultaDataDTO dataDTO) {
        return caixaRepository.findByData(dataDTO.getData()).orElse(null);
    }

    public MensagemDTO alterarCaixa(Long idCaixa, CaixaDTO caixaDTO) {

        Optional<Caixa> caixaProcurado = caixaRepository.findById(idCaixa);

        if (caixaProcurado.isEmpty()) {
            return new MensagemDTO(CAIXA_INEXISTENTE);
        }

        Caixa caixaAlterado = caixaProcurado.get();
        BeanUtils.copyProperties(caixaDTO, caixaAlterado);

        caixaRepository.save(caixaAlterado);
        return new MensagemDTO(CAIXA_ALTERADO_COM_SUCESSO);
    }

    public MensagemDTO removerCaixa(Long idCaixa) {
        if (caixaRepository.existsById(idCaixa)) {
            caixaRepository.deleteById(idCaixa);
            return new MensagemDTO(CAIXA_REMOVIDO_COM_SUCESSO);
        }

        return new MensagemDTO(CAIXA_INEXISTENTE);
    }

    // esse retorno poderia ser uma mensagemDTO em vez de caixa?
    public MensagemDTO fechamentoCaixa(Long idCaixa) {
        Optional<Caixa> caixaConsultado = caixaRepository.findById(idCaixa);

        if (caixaConsultado.isEmpty()) {
            return new MensagemDTO(CAIXA_INEXISTENTE);
        }

        Caixa caixa = caixaConsultado.get();

        if (!caixa.isCaixaAberto()) {
            return new MensagemDTO("Caixa já se encontra fechado!");
        }

        double somaSaldoInicialEValorTotalCaixa = caixa.getSaldoInicial() + caixa.getValorTotal();
        caixa.setValorTotal(somaSaldoInicialEValorTotalCaixa);
        caixa.setCaixaAberto(false);
        caixaRepository.save(caixa);

        Gestao gestao = buscarGestao().get();
        double novoCapitalSocial = gestao.getCapitalSocial() + somaSaldoInicialEValorTotalCaixa;
        gestao.setCapitalSocial(novoCapitalSocial);
        gestaoRepository.save(gestao);

        List<Estoque> verificaValiadeEmEstoques = (List<Estoque>) estoqueRepository.findAll();

        double somaProdutosVencidos = 0;

        for (Estoque estoque : verificaValiadeEmEstoques) {
            if (estoque.getDataValidade().equals(LocalDate.now())) {
                estoque.setPerda(true);
                estoque.setDisponibilidade(false);
                somaProdutosVencidos =
                        +estoque.getQuantidade() * estoque.getProduto().getValorCusto();
            }
        }

        if (somaProdutosVencidos > 0) {
            DespesaDTO despesaDTO = new DespesaDTO();
            despesaDTO.setDescricao("Produto com a data de validade vencido!");
            despesaDTO.setValor(somaProdutosVencidos);

            despesaService.adicionarDespesa(despesaDTO);
        }

        return new MensagemDTO("Caixa fechado com sucesso!");

    }

    private Optional<Gestao> buscarGestao() {
        List<Gestao> listaGestao = (List<Gestao>) gestaoRepository.findAll();

        return listaGestao.stream().findFirst();
    }

}
