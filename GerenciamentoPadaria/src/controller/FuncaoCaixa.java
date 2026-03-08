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
    private final Pagamento pagamento;
    private ProdutoVendido produtoVendido;
    private final List<ProdutoVendido> produtoVendidoList;
    private final List<String> cpfClientes;
    private final ProdutoVendidoDAO produtoVendidoDAO;
    private final ProdutoDAO produtoDAO;
    private Produto produto;
    private Cliente cliente;
    private final ClienteDAO clienteDAO;
    private Desconto desconto;
    private final DescontoDAO descontoDAO;
    private BigDecimal valorDesconto;
    private Connection conn;

    public FuncaoCaixa() {
        ErrosEmArquivo errosEmArquivo = new ErrosEmArquivo();
        produto = new Produto();
        produtoDAO = new ProdutoDAO();
        produtoVendido = new ProdutoVendido();
        produtoVendidoDAO = new ProdutoVendidoDAO();
        produtoVendidoList = new ArrayList<ProdutoVendido>();
        cpfClientes = new ArrayList<String>();
        cliente = new Cliente();
        clienteDAO = new ClienteDAO();
        desconto = new Desconto();
        descontoDAO = new DescontoDAO();
        pagamento = new Pagamento();
        errosEmArquivo.ativarSaidaErrosArquivo();
    }

    public void closeConn() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }
    }

    public void openConn() {
        conn = DbConnect.getInstance().getConexaoSQLITE();
        setConnectionAll(conn);
    }

    public void atualizarDescontoClienteCompra() {
        clienteDAO.atualizarDescontoClienteCompra(cpfClientes);
    }

    public String setCliente(String idCliente) throws IllegalArgumentException {
        if (clienteDAO.identificar(idCliente)) {
            cliente = clienteDAO.getCliente();
            cpfClientes.add(cliente.getIdCliente());
        } else {
            throw new IllegalArgumentException("Cliente não identificado");
        }
        if (descontoDAO.identificar(cliente.getNivelDesconto())) {
            desconto = descontoDAO.getDesconto();
        }
        return cliente.getNomeCliente();
    }

    private int identificarIdCompra() {
        return produtoVendidoDAO.retornarUltimoIdCompra() + 1;
    }

    public void registrarProduto(int idProduto, int quantidade) throws IllegalArgumentException {
        if (produtoDAO.identificar(idProduto)) {
            produto = produtoDAO.getProduto();

            BigDecimal valorPagoUnidade = new BigDecimal(produto.getValorProduto());
            if (desconto.getNivelDesconto() > 0) {
                BigDecimal porcentagemDescontoDivididoPorCem = new BigDecimal(String.valueOf(desconto.getPorcentagemDesconto() / 100D));
                valorDesconto = valorPagoUnidade.multiply(porcentagemDescontoDivididoPorCem);
                valorPagoUnidade = valorPagoUnidade.subtract(valorDesconto);
            }

            valorPagoUnidade = valorPagoUnidade.multiply(new BigDecimal(quantidade));

            valorPagoUnidade = valorPagoUnidade.setScale(2, RoundingMode.HALF_EVEN);

            produtoVendido = new ProdutoVendido();

            produtoVendido.setIdCompra(identificarIdCompra());
            produtoVendido.setIdCliente(cliente.getIdCliente());
            produtoVendido.setData(Data.getData());
            produtoVendido.setIdProduto(idProduto);
            produtoVendido.setQuantidade(quantidade);
            produtoVendido.setValorPago(String.valueOf(valorPagoUnidade));
            produtoVendido.setValorDesconto(String.valueOf(valorDesconto));

            produtoVendidoList.add(produtoVendido);
        } else {
            throw new IllegalArgumentException("Produto não encontrado");
        }
    }

    public String valorDescontado() throws IllegalArgumentException {
        BigDecimal valorPago, valorProduto, valorDescontado, quantidade;
        valorDescontado = BigDecimal.ZERO;

        for (ProdutoVendido produtoVendido : produtoVendidoList) {
            produtoDAO.identificar(produtoVendido.getIdProduto());
            Produto produto = produtoDAO.getProduto();

            quantidade = new BigDecimal(String.valueOf(produtoVendido.getQuantidade()));
            valorPago = new BigDecimal(produtoVendido.getValorPago());
            valorProduto = new BigDecimal(produto.getValorProduto());

            valorDescontado = valorDescontado.add(valorProduto.multiply(quantidade).subtract(valorPago));
        }
        return valorDescontado.toString();
    }

    public String valorDescontado(int indice) throws IllegalArgumentException {
        if (indice > 0) {
            BigDecimal valorPago, valorProduto, valorDescontado, quantidade;

            ProdutoVendido produtoVendido = produtoVendidoList.get(indice - 1);
            produtoDAO.identificar(produtoVendido.getIdProduto());
            Produto produto = produtoDAO.getProduto();

            quantidade = new BigDecimal(String.valueOf(produtoVendido.getQuantidade()));
            valorPago = new BigDecimal(produtoVendido.getValorPago());
            valorProduto = new BigDecimal(produto.getValorProduto());

            valorDescontado = valorProduto.multiply(quantidade).subtract(valorPago);

            return valorDescontado.toString();
        } else {
            throw new IllegalArgumentException("Índice negativo fornecido");
        }
    }

    public String valorTotal() {
        BigDecimal valorTotal, valorProduto, quantidade;
        quantidade = new BigDecimal(String.valueOf(produtoVendido.getQuantidade()));
        valorProduto = new BigDecimal(produto.getValorProduto());

        valorTotal = valorProduto.multiply(quantidade);
        return valorTotal.toString();
    }

    public String getNomeProduto(int id) {
        return produtoDAO.getNomeProduto(id);
    }

    public void cancelarItem(byte indice) {
        if(indice > 0) {
            produtoVendidoList.remove(indice - 1);
        } else {
            throw new IllegalArgumentException("Índice negativo fornecido");
        }
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

    public List<ProdutoVendido> getProdutoVendidoList() {
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