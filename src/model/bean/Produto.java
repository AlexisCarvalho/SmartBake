package model.bean;

public class Produto {

    private int idProduto;
    private String descricaoProduto, nomeProduto, valorProduto;

    public Produto(String nomeProduto, String descricaoProduto, String valorProduto) {
        this.idProduto = 0;
        this.valorProduto = valorProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
    }

    public Produto() {
        idProduto = 0;
        valorProduto = nomeProduto = descricaoProduto = "";
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(String valorProduto) {
        this.valorProduto = valorProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    @Override
    public String toString() {
        return "Estoque{"
                + "idProduto = " + idProduto
                + ", valorProduto = " + valorProduto
                + ", nomeProduto = " + nomeProduto
                + ", descricao = " + descricaoProduto + '}';
    }
}
