package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
    private static final String tableFluxoDeCaixa =
            "create table IF NOT EXISTS FluxoDeCaixa(" +
                    "idCompra integer," +
                    "idCliente text, " +
                    "data text," +
                    "idProduto integer," +
                    "quantidade integer," +
                    "valorPago text" +
                    ");";
    private static final String tableEstoque =
            "create table IF NOT EXISTS Estoque(" +
                    "idProduto integer primary key autoincrement," +
                    "valorProduto text," +
                    "nomeProduto text," +
                    "descricaoProduto text" +
                    ");";
    private static final String tableFuncionarios =
            "create table IF NOT EXISTS Funcionarios(" +
                    "idFuncionario integer primary key autoincrement," +
                    "login text," +
                    "hashSenha text," +
                    "salt text," +
                    "nivel integer" +
                    ");";
    private static final String tableClientes =
            "create table IF NOT EXISTS Clientes(" +
                    "idCliente text," +
                    "nomeCliente text," +
                    "nivelDesconto integer" +
                    ");";
    private static final String tablePlanoFidelidade =
            "create table IF NOT EXISTS PlanoFidelidade(" +
                    "nivelDesconto integer primary key autoincrement," +
                    "porcentagemDesconto integer," +
                    "valorRequisitado text" +
                    ");";
    private static final String tableDistribuicao =
            "create table IF NOT EXISTS DISTRIBUICAO(" +
                    "id_distribuicao integer," +
                    "lim_inferior text," +
                    "lim_superior text," +
                    "fi text," +
                    "xi text," +
                    "fri text," +
                    "fri_porcentagem text," +
                    "fac text," +
                    "fac_porcentagem text" +
                    ")";
    private static final String tableInformacoes =
            "create table IF NOT EXISTS INFORMACOES(" +
                    "id_informacoes integer primary key autoincrement," +
                    "id_distribuicao integer," +
                    "data text," +
                    "numero_de_dados integer," +
                    "xMax text," +
                    "xMin text," +
                    "log text," +
                    "classes text," +
                    "amplitude text," +
                    "amplitude_intervalos text" +
                    ")";
    
    DbConnect() {
    }

    public static Connection getConexaoSQLITE() {
        Connection connection = null;

        try {
            String myDatabase = "Padaria.db";
            String url = "jdbc:sqlite:" + myDatabase;

            connection = DriverManager.getConnection(url);

            if (connection != null) {
                Statement st = connection.createStatement();
                st.executeUpdate(tablePlanoFidelidade);
                st.executeUpdate(tableFluxoDeCaixa);
                st.executeUpdate(tableClientes);
                st.executeUpdate(tableEstoque);
                st.executeUpdate(tableFuncionarios);
                st.executeUpdate(tableDistribuicao);
                st.executeUpdate(tableInformacoes);
            } else {
                throw new SQLException();
            }
            return connection;

        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + "Nao foi possivel conectar ao banco de dados");
            return connection;
        }
    }

    private static boolean FecharConexao() {
        try {
            DbConnect.getConexaoSQLITE().close();
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }

    public static Connection ReiniciarConexao() {
        FecharConexao();
        return DbConnect.getConexaoSQLITE();
    }
}