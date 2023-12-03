package model.DAO;

import model.bean.Cliente;
import model.bean.Produto;
import util.Data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ClienteDAO {

    private Connection conn;
    private Cliente cliente;
    private ProdutoVendidoDAO produtoVendidoDAO;
    private DescontoDAO descontoDAO;

    public ClienteDAO() {
        conn = null;
        cliente = new Cliente();
        produtoVendidoDAO = new ProdutoVendidoDAO();
        descontoDAO = new DescontoDAO();
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
        produtoVendidoDAO.setConnection(conn);
        descontoDAO.setConnection(conn);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean identificar(String idCliente) {
        if (cliente.getNomeCliente() != null) {
            cliente = new Cliente();
        }
        String selectSQL = "SELECT * FROM Clientes WHERE idCliente = " + "'" + idCliente + "'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                cliente.setIdCliente(rs.getString("idCliente"));
                cliente.setNomeCliente(rs.getString("nomeCliente"));
                cliente.setNivelDesconto(rs.getInt("nivelDesconto"));
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return cliente.getNomeCliente() != null;
    }

    public boolean registrar(Cliente p) {
        String insertSQL = "INSERT INTO Clientes (idCliente, nomeCliente, nivelDesconto) VALUES ('"
                + p.getIdCliente() + "','"
                + p.getNomeCliente() + "','"
                + p.getNivelDesconto() + "')";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insertSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }

    public boolean update(Cliente p) {
        String updateSQL = "UPDATE Clientes SET "
                + "nomeCliente = '" + p.getNomeCliente() + "',"
                + "nivelDesconto = " + p.getNivelDesconto() + " "
                + "WHERE idCliente = '" + p.getIdCliente() + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(updateSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }

    public boolean delete(String idCliente) {
        String deleteSQL = "DELETE FROM Clientes "
                + "WHERE idCliente = '" + idCliente + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(deleteSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }
    
    public void atualizarDescontoDeTodos() {
        Cliente clienteTemp;
        BigDecimal gastoTotalTemp;
        int novoNivel;

        String selectSQL = "SELECT * FROM Clientes";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                clienteTemp = new Cliente();
                clienteTemp.setIdCliente(rs.getString("idCliente"));
                clienteTemp.setNomeCliente(rs.getString("nomeCliente"));
                clienteTemp.setNivelDesconto(rs.getInt("nivelDesconto"));

                gastoTotalTemp = produtoVendidoDAO.calcularValorGastoTotalCliente(clienteTemp.getIdCliente());
                novoNivel = descontoDAO.descobrirNivel(gastoTotalTemp.toString());
                clienteTemp.setNivelDesconto(novoNivel);

                update(clienteTemp);
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
    }
    
    public void tabelaClientes(Cliente cliente, DefaultTableModel modelo) {
		String selectSQL = "SELECT * FROM Clientes ORDER BY idCliente";
		for (int i = 0; i < modelo.getRowCount(); i++) {
			modelo.removeRow(i);
			i-=1;
			}
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectSQL);

			while (rs.next()) {
				cliente.setIdCliente(rs.getString("idCliente"));
				cliente.setNomeCliente(rs.getString("nomeCliente"));
				cliente.setNivelDesconto(rs.getInt("nivelDesconto"));

				modelo.addRow(new Object[] { cliente.getIdCliente(), cliente.getNomeCliente(), cliente.getNivelDesconto(),
						produtoVendidoDAO.calcularValorGastoTotalCliente(cliente.getIdCliente()) });

			}
		} catch (SQLException ex) {
			System.out.println("Erro SQL: " + ex.getMessage());
		} 
	}

    public List<Cliente> lista() {
        return new ArrayList<Cliente>();
    }
}