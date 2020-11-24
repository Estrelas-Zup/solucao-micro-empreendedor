package br.com.zup.estrelas.sme.enums;

public enum UnidadeMedida {
    UNIDADE("unidade"), KG("kg"), GRAMA("grama");

    private String unidadeMedida;

    private UnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}
