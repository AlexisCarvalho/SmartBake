package model.DAO;


import model.bean.Produto;
import util.Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ProdutoDAO {

    private Produto produto;
    private Connection conn;

    public ProdutoDAO() {
        produto = new Produto();
        conn = null;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public Produto getProduto() {
        return produto;
    }

    public boolean identificar(int idProduto) {
        if (produto.getNomeProduto() != null) {
            produto = new Produto();
        }
        String selectSQL = "SELECT * FROM Estoque WHERE idProduto = " + idProduto;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setValorProduto(rs.getString("valorProduto"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return produto.getNomeProduto() != null;
    }

    public boolean identificarPorNome(String nomeProduto) {
        if (produto.getIdProduto() != 0) {
            produto = new Produto();
        }
        String selectSQL = "SELECT * FROM Estoque WHERE nomeProduto = " + "'" + nomeProduto + "'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setValorProduto(rs.getString("valorProduto"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return produto.getIdProduto() != 0;
    }

    public boolean registrar(Produto p) {
        String insertSQL = "INSERT INTO Estoque (valorProduto, nomeProduto, descricaoProduto) VALUES ('"
                + p.getValorProduto() + "','"
                + p.getNomeProduto() + "','"
                + p.getDescricaoProduto() + "')";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insertSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }

    }

    public boolean update(Produto p) {
        String updateSQL = "UPDATE Estoque SET "
                + "valorProduto = '" + p.getValorProduto() + "',"
                + "nomeProduto = '" + p.getNomeProduto() + "',"
                + "descricaoProduto = '" + p.getDescricaoProduto() + "' "
                + "WHERE idProduto = '" + p.getIdProduto() + "'";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(updateSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }

    public boolean delete(int idProduto) {
        String deleteSQL = "DELETE FROM Estoque "
                + "WHERE idProduto = '" + idProduto + "'";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(deleteSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }
    
    public String getNomeProduto(int idProduto) {
        if (produto.getIdProduto() != 0) {
            produto = new Produto();
        }
        String selectSQL = "SELECT * FROM Estoque WHERE idProduto = " + "'" + idProduto + "'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                return (rs.getString("nomeProduto"));
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return "";
    }
    
	public void tabelaProdutos(Produto produto, DefaultTableModel modelo) {
		String selectSQL = "SELECT * FROM Estoque ORDER BY idProduto";
		for (int i = 0; i < modelo.getRowCount(); i++) {
			modelo.removeRow(i);
			i-=1;
			}
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectSQL);

			while (rs.next()) {
				produto.setIdProduto(rs.getInt("idProduto"));
				produto.setValorProduto(rs.getString("valorProduto"));
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setDescricaoProduto(rs.getString("descricaoProduto"));

				modelo.addRow(new Object[] { produto.getIdProduto(), produto.getValorProduto(), produto.getNomeProduto(),
						produto.getDescricaoProduto() });

			}
		} catch (SQLException ex) {
			System.out.println("Erro SQL: " + ex.getMessage());
		} 
	}

    public List<Produto> listarTodosOsProdutos() {
        List<Produto> todosOsProdutos = new ArrayList<Produto>();

        String selectSQL = "SELECT * FROM Estoque";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setValorProduto(rs.getString("valorProduto"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                todosOsProdutos.add(produto);
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return todosOsProdutos;
    }
}