package controller;

import model.DAO.FuncionarioDAO;
import model.Hashing;
import model.bean.Funcionario;
import util.DbConnect;

import java.sql.Connection;
import java.sql.SQLException;

public class FuncaoLogin {

    Funcionario funcionario;
    FuncionarioDAO funcionarioDAO;
    Connection conn;
    Hashing hashing;

    public FuncaoLogin() {
        funcionario = new Funcionario();
        funcionarioDAO = new FuncionarioDAO();
        hashing = new Hashing();
    }
    
    public void closeConn() throws SQLException {
    	conn.close();
    }
    
    public void openConn() {
    	conn = DbConnect.getConexaoSQLITE();
    	setConnectionAll(conn);
    }
    
    public void setConnectionAll(Connection conn) {
        funcionarioDAO.setConnection(conn);
    }

    public void autenticar(String login, String senha) throws IllegalArgumentException {
        funcionario = funcionarioDAO.lerPorLogin(login);
        if (funcionario.getIdFuncionario() != 0) {
            if (hashing.autenticacaoSenha(senha, funcionario)) {
            } else {
                throw new IllegalArgumentException("Senha informada incorreta");
            }
        } else {
            throw new IllegalArgumentException("Login informado inexistente");
        }
    }
    
    public int getNivel() {
    	return funcionario.getNivel();
    }
    
    public String getLogin() {
    	return funcionario.getLogin();
    }
}
