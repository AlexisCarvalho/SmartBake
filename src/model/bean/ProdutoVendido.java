package model.bean;

public class ProdutoVendido {

    private int idCompra, quantidade, idProduto;
    private String data, idCliente, valorPago;

    public ProdutoVendido(int idCompra, String idCliente, String data, int idProduto, int quantidade, String valorPago) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.data = data;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valorPago = valorPago;
    }

    public ProdutoVendido() {
        idCompra = quantidade = idProduto = 0;
        data = idCliente = valorPago = "";
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    @Override
    public String toString() {
        return + quantidade + "x" + " - Valor = " + valorPago;
    }
}