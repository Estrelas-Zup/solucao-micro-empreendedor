package br.com.zup.estrelas.sme.exceptions;

public class GenericException extends Exception{
    private static final long serialVersionUID = 1L;

    public GenericException(String mensagem) {
        super(mensagem);
    }
}
