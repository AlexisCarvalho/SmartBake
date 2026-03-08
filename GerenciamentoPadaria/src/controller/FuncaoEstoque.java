package controller;

import model.DAO.ProdutoDAO;
import model.bean.Produto;
import util.Data;
import util.DbConnect;
import util.ErrosEmArquivo;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FuncaoEstoque {
    private final ErrosEmArquivo errosEmArquivo;
    private final ProdutoDAO produtoDAO;
    private Produto produto;
    private Connection conn;

    public FuncaoEstoque() {
        produto = new Produto();
        produtoDAO = new ProdutoDAO();
        errosEmArquivo = new ErrosEmArquivo();
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

    public void setConnectionAll(Connection conn) {
        produtoDAO.setConnection(conn);
    }

    public List<Produto> listarProdutosEstoque() {
        return produtoDAO.listarTodosOsProdutos();
    }

    public void atualizarValorEmEstoque(int idProduto, String novoValor) throws IllegalArgumentException {
        if (produtoDAO.identificar(idProduto)) {
            produto = produtoDAO.getProduto();
            produto.setValorProduto(novoValor);
            produtoDAO.update(produto);
        } else {
            throw new IllegalArgumentException("Nao existe produto com esse ID no estoque");
        }
    }

    public void atualizarTudo(int id, String valor, String nome, String descricao) {
        if (produtoDAO.identificar(id)) {
            produto = produtoDAO.getProduto();
            produto.setValorProduto(valor);
            produto.setNomeProduto(nome);
            produto.setDescricaoProduto(descricao);
            produtoDAO.update(produto);
        } else {
            throw new IllegalArgumentException("ID não identificado");
        }
    }

    public void atualizarNomeEmEstoque(int idProduto, String novoNome) {
        if (produtoDAO.identificar(idProduto)) {
            produto = produtoDAO.getProduto();
            produto.setNomeProduto(novoNome);
            produtoDAO.update(produto);
        } else {
            throw new IllegalArgumentException("Nao existe produto com esse ID no estoque");
        }
    }

    public void atualizarNomeEmEstoque(int idProduto, String novoNome, String novaDescricao) {
        if (produtoDAO.identificar(idProduto)) {
            produto = produtoDAO.getProduto();
            produto.setNomeProduto(novoNome);
            produto.setDescricaoProduto(novaDescricao);
            produtoDAO.update(produto);
        } else {
            throw new IllegalArgumentException("Nao existe produto com esse ID no estoque");
        }
    }

    public void gerarTabela(Produto produto, DefaultTableModel modelo) {
        produtoDAO.tabelaProdutos(produto, modelo);
    }
}