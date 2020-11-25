package br.com.zup.estrelas.sme.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sme.dto.ConsultaDataDTO;
import br.com.zup.estrelas.sme.dto.CaixaDTO;
import br.com.zup.estrelas.sme.dto.MensagemDTO;
import br.com.zup.estrelas.sme.entity.Caixa;
import br.com.zup.estrelas.sme.repository.CaixaRepository;
import br.com.zup.estrelas.sme.service.CaixaService;

@Service
public class CaixaServiceImpl implements CaixaService {

    private static final String CAIXA_CADASTRADO_COM_SUCESSO = "Caixa cadastrado com sucesso!";
    private static final String CAIXA_INEXISTENTE = "Caixa inexistente!";
    private static final String CAIXA_REMOVIDO_COM_SUCESSO = "Caixa removido com sucesso!";
    private static final String CAIXA_ALTERADO_COM_SUCESSO = "Caixa alterado com sucesso!";

    @Autowired
    CaixaRepository caixaRepository;

    public MensagemDTO adicionarCaixa(CaixaDTO caixaDTO) {

        Caixa caixa = new Caixa();

        BeanUtils.copyProperties(caixaDTO, caixa);

        caixaRepository.save(caixa);
        return new MensagemDTO(CAIXA_CADASTRADO_COM_SUCESSO);
    }

    public List<Caixa> listarCaixa() {
        return (List<Caixa>) caixaRepository.findAll();
    }

    public Caixa consultarPorId(Long idCaixa) {
        return caixaRepository.findById(idCaixa).orElse(null);
    }

    public List<Caixa> consultarPelaData(ConsultaDataDTO dataDTO) {
        
        List<Caixa> caixaConsultado = caixaRepository.findByData(dataDTO.getData());

        if (caixaConsultado.isEmpty()) {
           return null; 
        }
        return caixaConsultado;
        
    }

    public MensagemDTO alteraCaixa(Long idCaixa, CaixaDTO caixaDTO) {

        Optional<Caixa> caixaProcurado = caixaRepository.findById(idCaixa);

        if (caixaProcurado.isPresent()) {

            Caixa caixaAlterado = caixaProcurado.get();
            BeanUtils.copyProperties(caixaDTO, caixaAlterado);

            caixaRepository.save(caixaAlterado);
            return new MensagemDTO(CAIXA_ALTERADO_COM_SUCESSO);
        }

        return new MensagemDTO(CAIXA_INEXISTENTE);
    }

    public MensagemDTO removerCaixa(Long idCaixa) {

        if (caixaRepository.existsById(idCaixa)) {
            caixaRepository.deleteById(idCaixa);
            return new MensagemDTO(CAIXA_REMOVIDO_COM_SUCESSO);
        }

        return new MensagemDTO(CAIXA_INEXISTENTE);
    }

}