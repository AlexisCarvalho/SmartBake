package model.DAO;

import model.bean.ProdutoVendido;
import util.Data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoVendidoDAO {

    Connection conn;

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public boolean registrar(ProdutoVendido p) {

        String insertSQL = "INSERT INTO FluxoDeCaixa (idCompra, idCliente, data, idProduto, quantidade, valorPago) VALUES ('"
                + p.getIdCompra() + "','"
                + p.getIdCliente() + "','"
                + p.getData() + "','"
                + p.getIdProduto() + "','"
                + p.getQuantidade() + "','"
                + p.getValorPago() + "')";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insertSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }

    public boolean delete(int idCompra) {
        String deleteSQL = "DELETE FROM FluxoDeCaixa "
                + "WHERE idCompra = '" + idCompra + "'";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(deleteSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }

    public List<ProdutoVendido> lerPorIdCompra(int idCompra) {
        ProdutoVendido produtoVendido = new ProdutoVendido();
        List<ProdutoVendido> produtosVendidosList = new ArrayList<ProdutoVendido>();
        String selectSQL = "SELECT * FROM FluxoDeCaixa WHERE idCompra = " + idCompra;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
            	produtoVendido = new ProdutoVendido();
            	produtoVendido.setIdCompra(rs.getInt("idCompra"));
            	produtoVendido.setIdCliente(rs.getString("idCliente"));
            	produtoVendido.setData(rs.getString("data"));
            	produtoVendido.setIdProduto(rs.getInt("idProduto"));
            	produtoVendido.setQuantidade(rs.getInt("quantidade"));
            	produtoVendido.setValorPago(rs.getString("valorPago"));
                produtosVendidosList.add(produtoVendido);
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return produtosVendidosList;
    }
    
    public List<ProdutoVendido> lerPorIdCliente(String idCliente) {
        ProdutoVendido produtoVendido;
        List<ProdutoVendido> produtosVendidosList = new ArrayList<ProdutoVendido>();
        String selectSQL = "SELECT * FROM FluxoDeCaixa WHERE idCliente = '" + idCliente + "'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                produtoVendido = new ProdutoVendido();
                produtoVendido.setIdCompra(rs.getInt("idCompra"));
                produtoVendido.setIdCliente(rs.getString("idCliente"));
                produtoVendido.setData(rs.getString("data"));
                produtoVendido.setIdProduto(rs.getInt("idProduto"));
                produtoVendido.setQuantidade(rs.getInt("quantidade"));
                produtoVendido.setValorPago(rs.getString("valorPago"));
                produtosVendidosList.add(produtoVendido);
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return produtosVendidosList;
    }
    
    public BigDecimal calcularValorGastoTotalCliente(String idCliente) {
        List<ProdutoVendido> produtosCliente = lerPorIdCliente(idCliente);
        BigDecimal valorTotalGasto = new BigDecimal("0");
        BigDecimal valorProduto;

        for (ProdutoVendido a : produtosCliente) {
            valorProduto = new BigDecimal(a.getValorPago());
            valorTotalGasto = valorTotalGasto.add(valorProduto);
        }
        return valorTotalGasto;
    }
    
    public BigDecimal calcularValorGastoTotalCompra(int idCompra) {
        List<ProdutoVendido> produtosCliente = lerPorIdCompra(idCompra);
        BigDecimal valorTotalGasto = new BigDecimal("0");
        BigDecimal valorProduto;

        for (ProdutoVendido a : produtosCliente) {
            valorProduto = new BigDecimal(a.getValorPago());
            valorTotalGasto = valorTotalGasto.add(valorProduto);
        }
        return valorTotalGasto;
    }

    public int retornarUltimoIdCompra() throws IllegalAccessException {
        String selectSQL = "SELECT * FROM FluxoDeCaixa ORDER BY idCompra DESC LIMIT 1";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            if (rs.next()) {
                return rs.getInt("idCompra");
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        throw new IllegalAccessException();
    }

    public List<ProdutoVendido> lista() {
        return new ArrayList<ProdutoVendido>();
    }
}