package model.DAO;

import model.bean.Funcionario;
import util.Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class FuncionarioDAO {

    Funcionario funcionario;
    Connection conn;
    
    public FuncionarioDAO() {
    	funcionario = new Funcionario();
    	conn = null;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public boolean identificar(String login) {
    	if(funcionario.getLogin() != null) {
    		funcionario = new Funcionario();
    	}
        String selectSQL = "SELECT * FROM Funcionarios WHERE login = '" + login+"'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionario.setLogin(rs.getString("login"));
                funcionario.setHashSenha(rs.getString("hashSenha"));
                funcionario.setSalt(rs.getString("salt"));
                funcionario.setNivel(rs.getInt("nivel"));
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return funcionario.getIdFuncionario() != 0;
    }
    
    public boolean identificarId(String id) {
    	if(funcionario.getLogin() != null) {
    		funcionario = new Funcionario();
    	}
        String selectSQL = "SELECT * FROM Funcionarios WHERE idFuncionario = '" + id +"'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionario.setLogin(rs.getString("login"));
                funcionario.setHashSenha(rs.getString("hashSenha"));
                funcionario.setSalt(rs.getString("salt"));
                funcionario.setNivel(rs.getInt("nivel"));
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return funcionario.getLogin() != null;
    }

    public boolean registrar(Funcionario p) {

        String insertSQL = "INSERT INTO Funcionarios (login, hashSenha, salt, nivel) VALUES ('"
                + p.getLogin() + "','"
                + p.getHashSenha() + "','"
                + p.getSalt() + "','"
                + p.getNivel() + "')";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insertSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }

    public boolean delete(String login) {
        String deleteSQL = "DELETE FROM Funcionarios "
                + "WHERE login = '" + login + "'";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(deleteSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }
    
    public boolean deleteId(String id) {
        String deleteSQL = "DELETE FROM Funcionarios "
                + "WHERE idFuncionario = '" + id + "'";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(deleteSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }

    public Funcionario lerPorIdFuncionario(int idFuncionario) {
        Funcionario p = new Funcionario();

        String selectSQL = "SELECT * FROM Funcionarios WHERE idFuncionario = " + idFuncionario;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                p.setIdFuncionario(rs.getInt("idFuncionario"));
                p.setLogin(rs.getString("loginUsuario"));
                p.setHashSenha(rs.getString("hashSenha"));
                p.setSalt(rs.getString("salt"));
                p.setNivel(rs.getInt("nivel"));
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return p;
    }

    public Funcionario lerPorLogin(String login) {
        Funcionario p = new Funcionario();

        String selectSQL = "SELECT * FROM Funcionarios WHERE login= " + "'" + login + "'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                p.setIdFuncionario(rs.getInt("idFuncionario"));
                p.setLogin(rs.getString("login"));
                p.setHashSenha(rs.getString("hashSenha"));
                p.setSalt(rs.getString("salt"));
                p.setNivel(rs.getInt("nivel"));
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return p;
    }
    
	public void tabelaFuncionarios(Funcionario funcionario, DefaultTableModel modelo) {
		String selectSQL = "SELECT * FROM Funcionarios ORDER BY idFuncionario";
		for (int i = 0; i < modelo.getRowCount(); i++) {
			modelo.removeRow(i);
			i-=1;
			}
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectSQL);

			while (rs.next()) {

                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionario.setLogin(rs.getString("login"));
                funcionario.setHashSenha(rs.getString("hashSenha"));
                funcionario.setSalt(rs.getString("salt"));
                funcionario.setNivel(rs.getInt("nivel"));
                
				modelo.addRow(new Object[] { funcionario.getIdFuncionario(), funcionario.getLogin(), funcionario.getHashSenha(),
						funcionario.getSalt(), funcionario.getNivel()});

			}
		} catch (SQLException ex) {
			System.out.println("Erro SQL: " + ex.getMessage());
		} 
	}

    public List<Funcionario> lista() {
        return new ArrayList<Funcionario>();
    }
}