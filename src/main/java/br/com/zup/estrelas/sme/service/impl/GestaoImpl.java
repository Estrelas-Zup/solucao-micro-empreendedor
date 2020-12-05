package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.AberturaComercioDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Gestao;
import br.com.zup.estrelas.sme.repository.GestaoRepository;
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
        
        return new MensagemDTO(COMERCIO_ENCERRADO_COM_SUCESSO + ", boa sorte em seu novo empredimento na Rua João Naves :D");
    }
}
