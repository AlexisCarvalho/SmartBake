package controller;

import model.DAO.InformacoesDAO;
import model.DAO.ProdutoVendidoDAO;
import model.DistribuicaoDeFrequencia;
import model.bean.Informacoes;
import model.bean.ProdutoVendido;
import util.Data;
import util.DbConnect;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Analise {

    Connection conn;
    private DistribuicaoDeFrequencia distriDeFreque;
    private BigDecimal[][] tabela;
    private Informacoes informacoes;
    private InformacoesDAO informacoesDAO;
    private ProdutoVendido produtoVendido;
    private ProdutoVendidoDAO produtoVendidoDAO;
    
    public void closeConn() throws SQLException {
    	conn.close();
    }
    
    public void openConn() {
    	conn = DbConnect.getConexaoSQLITE();
    	setConnectionAll(conn);
    }

    public Analise() {
        distriDeFreque = new DistribuicaoDeFrequencia();
        informacoes = new Informacoes();
        informacoesDAO = new InformacoesDAO();
        produtoVendidoDAO = new ProdutoVendidoDAO();
        produtoVendido = new ProdutoVendido();
        conn = null;
    }

    public void adicionarVariavelAoBanco(int id, String variavel) {
        //informacoesDAO.registrarVariavel(variavel);
    	produtoVendido = new ProdutoVendido(id, "12312312311", Data.getData(), 0, 1, variavel);
    	produtoVendidoDAO.registrar(produtoVendido);
    }

    public List<BigDecimal> getDadosBanco() {
        return informacoesDAO.listarDados();
    }

    public void gerarResultados(List<BigDecimal> list) {
        distriDeFreque.efetuarCalculos(list);
        informacoes = distriDeFreque.getInformacoes();
        informacoes.setData(String.valueOf(LocalDate.now()));
        tabela = informacoes.getTabela();
    }

    public void salvarNoBanco() {
        informacoesDAO.registrarInformacoes(informacoes);
    }

    public BigDecimal[][] getTabela() {
        return tabela;
    }

    public String getData() {
        return informacoes.getData();
    }

    public int getNumeroDeDadosColetados() {
        return informacoes.getNumeroDeDadosColetados().intValue();
    }

    public BigDecimal getMaiorVariavel() {
        return informacoes.getMaiorVariavel();
    }

    public BigDecimal getMenorVariavel() {
        return informacoes.getMenorVariavel();
    }

    public BigDecimal getLog() {
        return informacoes.getLog();
    }

    public int getClasses() {
        return informacoes.getClasses().intValue();
    }

    public BigDecimal getAmplitudeAmostral() {
        return informacoes.getAmplitude();
    }

    public BigDecimal getAmplitudeIntervalos() {
        return informacoes.getAmplitudeIntervalos();
    }

    public BigDecimal[] getLimitesInferiores() {
        BigDecimal[] limitesInferiores = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            limitesInferiores[smtr] = tabela[smtr][0];
        }
        return limitesInferiores;
    }

    public BigDecimal[] getLimitesSuperiores() {
        BigDecimal[] limitesSuperiores = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            limitesSuperiores[smtr] = tabela[smtr][1];
        }
        return limitesSuperiores;
    }

    public BigDecimal[] getFrequenciasAbsolutas() {
        BigDecimal[] frequenciasAbsolutas = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            frequenciasAbsolutas[smtr] = tabela[smtr][2];
        }
        return frequenciasAbsolutas;
    }

    public BigDecimal[] getPontosMedios() {
        BigDecimal[] pontosMedios = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            pontosMedios[smtr] = tabela[smtr][3];
        }
        return pontosMedios;
    }

    public BigDecimal[] getFrequenciasRelativas() {
        BigDecimal[] frequenciasRelativas = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            frequenciasRelativas[smtr] = tabela[smtr][4];
        }
        return frequenciasRelativas;
    }

    public BigDecimal[] getFrequenciasRelativasPorcentagem() {
        BigDecimal[] frequenciaRelativaPorcentagem = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            frequenciaRelativaPorcentagem[smtr] = tabela[smtr][5];
        }
        return frequenciaRelativaPorcentagem;
    }

    public BigDecimal[] getFrequenciasAcumuladas() {
        BigDecimal[] frequenciasAcumuladas = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            frequenciasAcumuladas[smtr] = tabela[smtr][6];
        }
        return frequenciasAcumuladas;
    }

    public BigDecimal[] getFrequenciasAcumuladasPorcentagem() {
        BigDecimal[] frequenciasAcumuladasPorcentagem = new BigDecimal[tabela.length];

        for (int smtr = 0; smtr < tabela.length; smtr++) {
            frequenciasAcumuladasPorcentagem[smtr] = tabela[smtr][7];
        }
        return frequenciasAcumuladasPorcentagem;
    }
    
    public Informacoes getInformacoes() {
    	return informacoes;
    }
    
    public void gerarTabelaDistriVendas(Informacoes informacoes, DefaultTableModel modelo) {
    	informacoesDAO.tabelaDistriVendas(informacoes, modelo);
    }
    
    public void setConnectionAll(Connection conn) {
        informacoesDAO.setConnection(conn);
        produtoVendidoDAO.setConnection(conn);
    }
}