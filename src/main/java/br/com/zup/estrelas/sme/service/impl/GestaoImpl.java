package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.EstruturaRelatorioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.dto.RelatorioPrejuizoUnitarioProdutoDTO;
import br.com.zup.estrelas.sme.dto.RelatorioSugestaoNovoPrecoVendaDTO;
import br.com.zup.estrelas.sme.entity.Gestao;
import br.com.zup.estrelas.sme.entity.Produto;
import br.com.zup.estrelas.sme.repository.EstoqueRepository;
import br.com.zup.estrelas.sme.repository.GestaoRepository;
import br.com.zup.estrelas.sme.repository.ProdutoRepository;
import br.com.zup.estrelas.sme.repository.RelatorioVendaRepository;
import br.com.zup.estrelas.sme.service.GestaoService;

@Service
public class GestaoImpl implements GestaoService {

    private static final String COMERCIO_ENCERRADO_COM_SUCESSO = "Comercio encerrado com sucesso!";

    private static final String NENHUM_COMERCIO_ABERTO =
            "Infelizmente não foi possivel realizar a operação, gestão não possui comercio aberto.";

    private static final String ALTERAÇÃO_REALIZADA_COM_SUCESSO =
            "Investimento adicionado com sucesso!";

    private static final String ABERTURA_DO_COMERCIO_JA_REALIZADA =
            "Você já realizou a abertura de seu comercio, não pode realizar abertura novamente";

    private static final String BOM_TRABALHO_E_BOA_SORTE =
            "Abertura de comercio inicializada, bom trabalho e boa sorte!";

    @Autowired
    GestaoRepository gestaoRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    RelatorioVendaRepository relatorioVendaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public MensagemDTO aberturaComercio(AberturaComercioDTO aberturaComercioDTO) {
        Long quantidadeGestao = gestaoRepository.count();
        boolean verificaGestaoNaoCriada = quantidadeGestao == 0;

        if (verificaGestaoNaoCriada) {
            Gestao gestao = new Gestao();
            BeanUtils.copyProperties(aberturaComercioDTO, gestao);

            gestaoRepository.save(gestao);

            return new MensagemDTO(BOM_TRABALHO_E_BOA_SORTE);
        }

        return new MensagemDTO(ABERTURA_DO_COMERCIO_JA_REALIZADA);
    }

    public MensagemDTO adicionarInvestimentoCapitalSocial(AberturaComercioDTO aberturaComercioDTO) {
        List<Gestao> listaGestaoConsultada = (List<Gestao>) gestaoRepository.findAll();

        Optional<Gestao> gestaoConsultada = listaGestaoConsultada.stream().findFirst();

        Gestao novaGestao = gestaoConsultada.get();
        Double novoValorCapitalSocial =
                novaGestao.getCapitalSocial() + aberturaComercioDTO.getCapitalSocial();

        novaGestao.setCapitalSocial(novoValorCapitalSocial);
        gestaoRepository.save(novaGestao);

        return new MensagemDTO(ALTERAÇÃO_REALIZADA_COM_SUCESSO);
    }

    public MensagemDTO encerrarComercio() {
        List<Gestao> listaGestaoConsultada = (List<Gestao>) gestaoRepository.findAll();

        Optional<Gestao> gestaoConsultada = listaGestaoConsultada.stream().findFirst();

        if (gestaoConsultada.isEmpty()) {
            return new MensagemDTO(NENHUM_COMERCIO_ABERTO);
        }

        Gestao gestaoASerExcluida = gestaoConsultada.get();

        gestaoRepository.delete(gestaoASerExcluida);

        return new MensagemDTO(COMERCIO_ENCERRADO_COM_SUCESSO
                + ", boa sorte em seu novo empredimento na Rua João Naves :D");
    }

    public RelatorioSugestaoNovoPrecoVendaDTO calcularPrecoVendaPorProduto(Long idProduto) {
        Optional<Produto> produtoConsultado = produtoRepository.findById(idProduto);

        if (produtoConsultado.isEmpty()) {
            return null;
        }

        Produto produto = produtoConsultado.get();

        EstruturaRelatorioDTO estruturaRelatorio = montarEstruturaRelatorios(idProduto, produto);

        RelatorioSugestaoNovoPrecoVendaDTO relatorioSugestaoNovoPrecoVendaDTO =
                new RelatorioSugestaoNovoPrecoVendaDTO();

        BeanUtils.copyProperties(estruturaRelatorio, relatorioSugestaoNovoPrecoVendaDTO);

        relatorioSugestaoNovoPrecoVendaDTO =
                montarObjetoRelatorioSugestaoNovoPrecoVendaDTO(relatorioSugestaoNovoPrecoVendaDTO);

        return relatorioSugestaoNovoPrecoVendaDTO;
    }

    public RelatorioPrejuizoUnitarioProdutoDTO calcularPrejuizoUnitarioPorProduto(Long idProduto) {
        Optional<Produto> produtoConsultado = produtoRepository.findById(idProduto);

        if (produtoConsultado.isEmpty()) {
            return null;
        }

        Produto produto = produtoConsultado.get();

        EstruturaRelatorioDTO estruturaRelatorio = montarEstruturaRelatorios(idProduto, produto);

        RelatorioPrejuizoUnitarioProdutoDTO relatorioPrejuizoUnitarioProdutoDTO =
                new RelatorioPrejuizoUnitarioProdutoDTO();

        BeanUtils.copyProperties(estruturaRelatorio, relatorioPrejuizoUnitarioProdutoDTO);

        relatorioPrejuizoUnitarioProdutoDTO = montarObjetoRelatorioPrejuizoUnitarioProdutoDTO(
                relatorioPrejuizoUnitarioProdutoDTO);

        return relatorioPrejuizoUnitarioProdutoDTO;
    }

    public EstruturaRelatorioDTO montarEstruturaRelatorios(Long idProduto, Produto produto) {
        EstruturaRelatorioDTO estruturaRelatorioDTO = new EstruturaRelatorioDTO();

        estruturaRelatorioDTO.setTotalQuantidadeVendida(
                relatorioVendaRepository.findByIdProdutoEstoque(idProduto));
        estruturaRelatorioDTO.setTotalQuantidadePerdida(
                estoqueRepository.findEstoqueByIdProdutoEstoque(idProduto));
        estruturaRelatorioDTO.setPrecoCusto(produto.getValorCusto());
        estruturaRelatorioDTO.setPrecoVendaAtual(produto.getValorVenda());

        return estruturaRelatorioDTO;
    }

    public RelatorioSugestaoNovoPrecoVendaDTO montarObjetoRelatorioSugestaoNovoPrecoVendaDTO(
            RelatorioSugestaoNovoPrecoVendaDTO relatorioSugestaoNovoPrecoVendaDTO) {

        Integer totalQuantidadeProduzida =
                relatorioSugestaoNovoPrecoVendaDTO.getTotalQuantidadeVendida()
                        + relatorioSugestaoNovoPrecoVendaDTO.getTotalQuantidadePerdida();

        Double lucroPorProduto = relatorioSugestaoNovoPrecoVendaDTO.getPrecoVendaAtual()
                - relatorioSugestaoNovoPrecoVendaDTO.getPrecoCusto();

        Double valorTotalPerda =
                relatorioSugestaoNovoPrecoVendaDTO.getTotalQuantidadePerdida() * lucroPorProduto;

        relatorioSugestaoNovoPrecoVendaDTO.setSugestaoNovoPrecoVenda(
                valorTotalPerda / relatorioSugestaoNovoPrecoVendaDTO.getTotalQuantidadeVendida()
                        + relatorioSugestaoNovoPrecoVendaDTO.getPrecoVendaAtual());

        relatorioSugestaoNovoPrecoVendaDTO.setTotalQuantidadeProduzida(totalQuantidadeProduzida);

        return relatorioSugestaoNovoPrecoVendaDTO;
    }

    public RelatorioPrejuizoUnitarioProdutoDTO montarObjetoRelatorioPrejuizoUnitarioProdutoDTO(
            RelatorioPrejuizoUnitarioProdutoDTO relatorioPrejuizoUnitarioProdutoDTO) {

        Double valorTotalPrecoVendaPerda =
                relatorioPrejuizoUnitarioProdutoDTO.getTotalQuantidadePerdida()
                        * relatorioPrejuizoUnitarioProdutoDTO.getPrecoVendaAtual();

        Double valorTotalPrecoCustoPerda =
                relatorioPrejuizoUnitarioProdutoDTO.getTotalQuantidadePerdida()
                        * relatorioPrejuizoUnitarioProdutoDTO.getPrecoCusto();

        relatorioPrejuizoUnitarioProdutoDTO
                .setValorTotalLucroPerdido(valorTotalPrecoVendaPerda - valorTotalPrecoCustoPerda);
        relatorioPrejuizoUnitarioProdutoDTO.setValorPrejuizoUnitario(
                relatorioPrejuizoUnitarioProdutoDTO.getValorTotalLucroPerdido()
                        / relatorioPrejuizoUnitarioProdutoDTO.getTotalQuantidadePerdida());;

        return relatorioPrejuizoUnitarioProdutoDTO;
    }
}
