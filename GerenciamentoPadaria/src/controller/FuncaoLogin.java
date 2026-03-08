package controller;

import model.DAO.FuncionarioDAO;
import model.Hashing;
import model.bean.Funcionario;
import util.Data;
import util.DbConnect;

import java.sql.Connection;
import java.sql.SQLException;

public class FuncaoLogin {

    private Funcionario funcionario;
    private final FuncionarioDAO funcionarioDAO;
    private Connection conn;
    private final Hashing hashing;

    public FuncaoLogin() {
        funcionario = new Funcionario();
        funcionarioDAO = new FuncionarioDAO();
        hashing = new Hashing();
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
