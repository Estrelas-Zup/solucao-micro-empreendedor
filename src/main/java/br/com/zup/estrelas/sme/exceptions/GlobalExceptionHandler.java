package br.com.zup.estrelas.sme.exceptions;

import static java.util.Objects.nonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import br.com.zup.estrelas.sme.dto.ErrorDTO;
import br.com.zup.estrelas.sme.dto.ResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final int STR_NOME_DO_CAMPO = 0;
    private static final int IGNORA_POS_PONTO = 1;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody List<ErrorDTO> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        List<ErrorDTO> errosDeValidacao = new ArrayList<>();

        for (ObjectError erro : e.getBindingResult().getAllErrors()) {

            if (nonNull(erro.getCodes()) && nonNull(erro.getCodes()[STR_NOME_DO_CAMPO])) {
                String nomeCampo = erro.getCodes()[STR_NOME_DO_CAMPO];

                StringBuilder mensagemASerExibida = new StringBuilder();
                mensagemASerExibida.append("[");
                mensagemASerExibida.append(nomeCampo
                        .substring(nomeCampo.lastIndexOf(".") + IGNORA_POS_PONTO).toUpperCase());
                mensagemASerExibida.append("] - ");
                mensagemASerExibida.append(erro.getDefaultMessage());

                errosDeValidacao.add(new ErrorDTO(mensagemASerExibida.toString()));
            }
        }

        return errosDeValidacao;
    }


    /*
     * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     * 
     * @ExceptionHandler(RuntimeException.class) public @ResponseBody ErrorDTO
     * runtimeExceptionError(RuntimeException e) { StringBuilder mensagemASerExibida = new
     * StringBuilder();
     * 
     * mensagemASerExibida.append("Erro interno no servidor, contate o administrador do sistema.");
     * 
     * return new ErrorDTO(mensagemASerExibida.toString()); }
     */


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({GenericException.class})
    public @ResponseBody ResponseDTO handlerRegrasDeNegocio(Exception e) {
        return new ResponseDTO(e.getMessage());
    }
}
