package br.com.zup.estrelas.sme.dto;

public class ResponseDTO {
    private String mensagem;

    public ResponseDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResponseDTO other = (ResponseDTO) obj;
        if (mensagem == null) {
            if (other.mensagem != null)
                return false;
        } else if (!mensagem.equals(other.mensagem))
            return false;
        return true;
    }
}
