package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.DespesaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Despesa;
import br.com.zup.estrelas.sme.repository.DespesaRepository;
import br.com.zup.estrelas.sme.service.DespesaService;

@Service
public class DespesaServiceImpl implements DespesaService {

    private static final String DESPESA_CADASTRADA_COM_SUCESSO = "Despesa cadastrada com sucesso!";
    private static final String DESPESA_INEXISTENTE = "Despesa inexistente!";
    private static final String DESPESA_REMOVIDA_COM_SUCESSO = "Despesa removida com sucesso!";
    private static final String DESPESA_ALTERADA_COM_SUCESSO = "Despesa alterada com sucesso!";

    @Autowired
    DespesaRepository despesaRepository;

    public MensagemDTO adicionarDespesa(DespesaDTO despesaDTO) {

        Despesa despesa = new Despesa();

        BeanUtils.copyProperties(despesaDTO, despesa);

        despesaRepository.save(despesa);
        return new MensagemDTO(DESPESA_CADASTRADA_COM_SUCESSO);
    }

    public List<Despesa> listarDespesa() {
        return (List<Despesa>) despesaRepository.findAll();
    }

    public MensagemDTO alterarDespesa(Long idDespesa, DespesaDTO despesaDTO) {

        Optional<Despesa> despesaProcurada = despesaRepository.findById(idDespesa);

        if (despesaProcurada.isPresent()) {

            Despesa despesaAlterada = despesaProcurada.get();
            BeanUtils.copyProperties(despesaDTO, despesaAlterada);

            despesaRepository.save(despesaAlterada);

            return new MensagemDTO(DESPESA_ALTERADA_COM_SUCESSO);
        }

        return new MensagemDTO(DESPESA_INEXISTENTE);
    }

    public MensagemDTO removerDespesa(Long idDespesa) {

        if (despesaRepository.existsById(idDespesa)) {
            despesaRepository.deleteById(idDespesa);
            return new MensagemDTO(DESPESA_REMOVIDA_COM_SUCESSO);
        }

        return new MensagemDTO(DESPESA_INEXISTENTE);
    }
}
