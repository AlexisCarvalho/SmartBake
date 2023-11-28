package controller;

import model.DAO.*;
import model.Hashing;
import model.bean.Cliente;
import model.bean.Funcionario;
import model.bean.Produto;
import model.bean.ProdutoVendido;
import util.DbConnect;
import util.ErrosEmArquivo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class FuncaoGerente {

    ErrosEmArquivo errosEmArquivo;
    Connection conn;
    DescontoDAO descDAO;

    Produto produto;
    ProdutoDAO produtoDAO;
    ProdutoVendido produtoVendido;
    ProdutoVendidoDAO produtoVendidoDAO;
    ClienteDAO clienteDAO;
    Funcionario funcionario;
    FuncionarioDAO funcionarioDAO;
    Hashing hashing;

    public FuncaoGerente() {
        descDAO = new DescontoDAO();
        produto = new Produto();
        produtoDAO = new ProdutoDAO();
        clienteDAO = new ClienteDAO();
        funcionario = new Funcionario();
        funcionarioDAO = new FuncionarioDAO();
        errosEmArquivo = new ErrosEmArquivo();
        hashing = new Hashing();
        produtoVendido = new ProdutoVendido();
        produtoVendidoDAO = new ProdutoVendidoDAO();
        errosEmArquivo.ativarSaidaErrosArquivo();
    }
    
    public void closeConn() throws SQLException {
    	conn.close();
    }
    
    public void openConn() {
    	conn = DbConnect.getConexaoSQLITE();
    	setConnectionAll(conn);
    }

    public void cadastrarCliente(String idCliente, String nomeCliente, int nivelDesconto) throws IllegalArgumentException {
        Cliente cliente = new Cliente(idCliente, nomeCliente, nivelDesconto);
        if (!clienteDAO.identificar(idCliente)) {
            clienteDAO.registrar(cliente);
        } else {
            throw new IllegalArgumentException("Cliente com o mesmo CPF ja cadastrado");
        }
    }

    public void atualizarCliente(String idCliente, String nomeCliente, int nivelDesconto) throws IllegalArgumentException {
        Cliente cliente = new Cliente(idCliente, nomeCliente, nivelDesconto);
        if (clienteDAO.identificar(idCliente)) {
            clienteDAO.update(cliente);
        } else {
            throw new IllegalArgumentException("Cliente com o CPF informado nao cadastrado");
        }
    }

    public void deletarCliente(String idCliente) throws IllegalArgumentException {
        if (clienteDAO.identificar(idCliente)) {
            clienteDAO.delete(idCliente);
        } else {
            throw new IllegalArgumentException("Cliente nao existe no banco");
        }
    }

    public void cadastrarPlanoFidelidade(int porcentagemDesconto, String valorRequisitado) throws IllegalArgumentException {
        if (!descDAO.lerPorPorcentagem(porcentagemDesconto)) {
            descDAO.registrar(porcentagemDesconto, valorRequisitado);
        } else {
            throw new IllegalArgumentException("Plano com essa porcentagem de desconto existente: Plano " + descDAO.getDesconto().getNivelDesconto());
        }
    }

    public void atualizarPlanoFidelidade(int nivelDesconto, int porcentagemDesconto, String valorRequisitado) throws IllegalArgumentException {
        if (descDAO.identificar(nivelDesconto)) {
            descDAO.update(nivelDesconto, porcentagemDesconto, valorRequisitado);
        } else {
            throw new IllegalArgumentException("Plano com essa porcentagem de desconto nao existente");
        }
    }

    public void cadastrarProduto(String valor, String nome, String descricao) throws IllegalArgumentException {
        Produto produto = new Produto(nome, descricao, valor);
        if (!produtoDAO.identificarPorNome(nome)) {
            produtoDAO.registrar(produto);
        } else {
            throw new IllegalArgumentException("Existe um produto com o mesmo nome");
        }
    }

    public void deletarProduto(int idProduto) throws IllegalArgumentException{
        if(produtoDAO.identificar(idProduto)){
            produtoDAO.delete(idProduto);
        } else{
            throw new IllegalArgumentException("Produto com o id informado nao existe");
        }
    }

    public void cadastrarFuncionario(String login, String senha, int nivel) throws IllegalArgumentException{
        funcionario = new Funcionario();
        funcionario = funcionarioDAO.lerPorLogin(login);
        if(funcionario.getIdFuncionario() == 0) {
            funcionario = hashing.gerarHashSenha(senha);
            funcionario.setLogin(login);
            funcionario.setNivel(nivel);
            funcionarioDAO.registrar(funcionario);
        } else {
            throw new IllegalArgumentException("Login ja cadastrado");
        }
    }

    public void deletarFuncionario(String login) throws IllegalArgumentException {
        if(funcionarioDAO.identificar(login)){
            funcionarioDAO.delete(login);
        } else{
            throw new IllegalArgumentException();
        }
    }
    
    public void deletarFuncionarioId(String id) {
        if(funcionarioDAO.identificarId(id)){
            funcionarioDAO.deleteId(id);
        } else{
            throw new IllegalArgumentException();
        }
    }
    
    public void atualizarDescontoDeTodos() {
        clienteDAO.atualizarDescontoDeTodos();
    }

    public String calcularGastosCliente(String idCliente) {
        return produtoVendidoDAO.calcularValorGastoTotal(idCliente).toString();
    }
    
    public void gerarTabelaFunc(Funcionario funcionario, DefaultTableModel modelo) {
    	funcionarioDAO.tabelaFuncionarios(funcionario, modelo);
    }

    public void setConnectionAll(Connection conn) {
        descDAO.setConnection(conn);
        produtoDAO.setConnection(conn);
        clienteDAO.setConnection(conn);
        funcionarioDAO.setConnection(conn);
        produtoVendidoDAO.setConnection(conn);
    }
}