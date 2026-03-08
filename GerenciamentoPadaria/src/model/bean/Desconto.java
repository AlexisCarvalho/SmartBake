package model.bean;

public class Desconto {
    private int nivelDesconto, porcentagemDesconto;
    private String valorRequisitado;

    public Desconto(int nivelDesconto, int porcentagemDesconto, String valorRequisitado) {
        this.nivelDesconto = nivelDesconto;
        this.porcentagemDesconto = porcentagemDesconto;
        this.valorRequisitado = valorRequisitado;
    }

    public Desconto() {
        nivelDesconto = 0;
        porcentagemDesconto = 0;
        valorRequisitado = "";
    }

    public int getNivelDesconto() {
        return nivelDesconto;
    }

    public void setNivelDesconto(int nivelDesconto) {
        this.nivelDesconto = nivelDesconto;
    }

    public int getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(int porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public String getValorRequisitado() {
        return valorRequisitado;
    }

    public void setValorRequisitado(String valorRequisitado) {
        this.valorRequisitado = valorRequisitado;
    }

    @Override
    public String toString() {
        return "PlanoFidelidade{" + "nivelDesconto=" + nivelDesconto + ", porcentagemDesconto=" + porcentagemDesconto + '}';
    }
}