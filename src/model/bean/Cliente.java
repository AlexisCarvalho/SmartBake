package model.bean;

public class Cliente {

    private int nivelDesconto;
    private String nomeCliente, idCliente;

    public Cliente(String idCliente, String nomeCliente, int nivelDesconto) {
        this.idCliente = idCliente;
        this.nivelDesconto = nivelDesconto;
        this.nomeCliente = nomeCliente;
    }

    public Cliente() {
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getNivelDesconto() {
        return nivelDesconto;
    }

    public void setNivelDesconto(int nivelDesconto) {
        this.nivelDesconto = nivelDesconto;
    }

    @Override
    public String toString() {
        return "registroUsuario{"
                + "idCliente=" + idCliente
                + ", nomeCliente=" + nomeCliente
                + ", nivelDesconto=" + nivelDesconto + '}';
    }
}
