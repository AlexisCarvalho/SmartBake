package controller;

import model.DAO.ClienteDAO;
import model.DAO.DescontoDAO;
import model.DAO.ProdutoDAO;
import model.DAO.ProdutoVendidoDAO;
import model.Pagamento;
import model.bean.Cliente;
import model.bean.Desconto;
import model.bean.Produto;
import model.bean.ProdutoVendido;
import util.Data;
import util.DbConnect;
import util.ErrosEmArquivo;
import util.RetornarTroco;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncaoCaixa {

    private ErrosEmArquivo errosEmArquivo;
    private Pagamento pagamento;
    private ProdutoVendido produtoVendido;
    private List<ProdutoVendido> produtoVendidoList;
    private ProdutoVendidoDAO produtoVendidoDAO;
    private ProdutoDAO produtoDAO;
    private Produto produto;
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private Desconto desconto;
    private DescontoDAO descontoDAO;
    private FuncaoGerente funcaoGerente;
    private Connection conn;
    private final double cem = 100;

    public FuncaoCaixa() {
        errosEmArquivo = new ErrosEmArquivo();
        produto = new Produto();
        produtoDAO = new ProdutoDAO();
        produtoVendido = new ProdutoVendido();
        produtoVendidoDAO = new ProdutoVendidoDAO();
        produtoVendidoList = new ArrayList<ProdutoVendido>();
        cliente = new Cliente();
        clienteDAO = new ClienteDAO();
        desconto = new Desconto();
        descontoDAO = new DescontoDAO();
        pagamento = new Pagamento();
        errosEmArquivo.ativarSaidaErrosArquivo();
    }
    
    public void closeConn() throws SQLException {
    	conn.close();
    }
    
    public void openConn() {
    	conn = DbConnect.getConexaoSQLITE();
    	setConnectionAll(conn);
    }
    
    public void atualizarDescontoDeTodos() {
    	clienteDAO.atualizarDescontoDeTodos();
    }

    public String setCliente(String idCliente) throws IllegalArgumentException {
        if (clienteDAO.identificar(idCliente)) {
            cliente = clienteDAO.getCliente();
        } else {
            throw new IllegalArgumentException("Cliente não identificado");
        }
        if (descontoDAO.identificar(cliente.getNivelDesconto())) {
            desconto = descontoDAO.getDesconto();
        }
        return cliente.getNomeCliente();
    }

    private int identificarIdCompra() {
        try {
            return produtoVendidoDAO.retornarUltimoIdCompra() + 1;
        } catch (IllegalAccessException e) {
            return 1;
        }
    }

    public void registrarProduto(int idProduto, int quantidade) throws IllegalArgumentException {
        if (produtoDAO.identificar(idProduto)) {
            produto = produtoDAO.getProduto();

            BigDecimal valorPago = new BigDecimal(produto.getValorProduto());
            if (desconto.getNivelDesconto() > 0) {
                valorPago = valorPago.subtract(valorPago.multiply(new BigDecimal(String.valueOf(desconto.getPorcentagemDesconto() / cem))));
                valorPago = valorPago.setScale(2, RoundingMode.HALF_EVEN);
            }

            produtoVendido = new ProdutoVendido(identificarIdCompra(), cliente.getIdCliente(), Data.getData(), idProduto, quantidade, String.valueOf(valorPago.multiply(new BigDecimal(quantidade))));
            produtoVendidoList.add(produtoVendido);
        } else {
            throw new IllegalArgumentException("Produto nao encontrado");
        }
    }

    public String valorDescontado(){
        BigDecimal valorPago, valorProduto, valorDescontado, quantidade;
        quantidade = new BigDecimal(String.valueOf(produtoVendido.getQuantidade()));
        valorPago = new BigDecimal(produtoVendido.getValorPago());
        valorProduto = new BigDecimal(produto.getValorProduto());

        valorDescontado = valorProduto.multiply(quantidade).subtract(valorPago);
        return valorDescontado.toString();
    }

    public String valorTotal(){
        BigDecimal valorTotal,valorProduto, quantidade;
        quantidade = new BigDecimal(String.valueOf(produtoVendido.getQuantidade()));
        valorProduto = new BigDecimal(produto.getValorProduto());

        valorTotal = valorProduto.multiply(quantidade);
        return valorTotal.toString();
    }
    
    public String getNomeProduto(int id) {
    	return produtoDAO.getNomeProduto(id);
    }
    
    public void cancelarItem(byte indice) {
        produtoVendidoList.remove(indice);
    }

    public String valorAReceber(boolean calcular) {
        if (calcular) {
            pagamento.calcularValorAReceber(produtoVendidoList);
        }
        return pagamento.getValorAReceber();
    }

    public boolean valorAReceber() {
        return !pagamento.getValorAReceber().equals("0.00");
    }

    public void receber(String valor) throws RetornarTroco {
        pagamento.receber(valor);
    }

    public String valorTroco() {
        pagamento.calcularTroco();
        return pagamento.getTroco();
    }
    
    public List<ProdutoVendido> getProdutoVendidoList(){
    	return produtoVendidoList;
    }

    public void setConnectionAll(Connection conn) {
        clienteDAO.setConnection(conn);
        produtoDAO.setConnection(conn);
        produtoVendidoDAO.setConnection(conn);
        descontoDAO.setConnection(conn);
    }

    public void sendToDB() {
        for (ProdutoVendido produto : produtoVendidoList) {
            produtoVendidoDAO.registrar(produto);
        }
    }
}