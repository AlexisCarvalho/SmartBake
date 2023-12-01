package model.DAO;

import model.bean.Desconto;
import util.Data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DescontoDAO {

    private Connection conn;
    private Desconto desconto;

    public DescontoDAO() {
        conn = null;
        desconto = new Desconto();
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public Desconto getDesconto() {
        return desconto;
    }

    public boolean identificar(int nivelDesconto) {
        if (desconto.getPorcentagemDesconto() != 0) {
            desconto = new Desconto();
        }
        String selectSQL = "SELECT * FROM PlanoFidelidade WHERE nivelDesconto = " + nivelDesconto;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                desconto.setNivelDesconto(rs.getInt("nivelDesconto"));
                desconto.setPorcentagemDesconto(rs.getInt("porcentagemDesconto"));
                desconto.setValorRequisitado(rs.getString("valorRequisitado"));
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return desconto.getPorcentagemDesconto() != 0;
    }

    public boolean registrar(int porcentagemDesconto, String valorRequisitado) {

        String insertSQL = "INSERT INTO PlanoFidelidade (porcentagemDesconto, valorRequisitado) VALUES ('"
                + porcentagemDesconto + "','"
                + valorRequisitado + "')";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insertSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }

    public boolean update(int nivelDesconto, int porcentagemDesconto, String valorRequisitado) {
        String updateSQL = "UPDATE PlanoFidelidade SET "
                + "porcentagemDesconto = '" + porcentagemDesconto + "', "
                + "valorRequisitado = '" + valorRequisitado + "' "
                + "WHERE nivelDesconto = '" + nivelDesconto + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(updateSQL);
            return true;
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
            return false;
        }
    }

    public boolean lerPorPorcentagem(int porcentagemDesconto) {
        if (desconto.getNivelDesconto() != 0) {
            desconto = new Desconto();
        }
        String selectSQL = "SELECT * FROM PlanoFidelidade WHERE porcentagemDesconto = " + porcentagemDesconto;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                desconto.setNivelDesconto(rs.getInt("nivelDesconto"));
                desconto.setPorcentagemDesconto(rs.getInt("porcentagemDesconto"));
                desconto.setValorRequisitado(rs.getString("valorRequisitado"));
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return desconto.getNivelDesconto() != 0;
    }
    
    public int descobrirNivel(String valorTotalStr) {
        String selectSQL = "SELECT * FROM PlanoFidelidade";
        BigDecimal valorRequisitado, valorTotal;
        valorTotal = new BigDecimal(valorTotalStr);
        Desconto descontoTemp;
        int nivel = 0;

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);
            while (rs.next()) {
                descontoTemp = new Desconto();
                descontoTemp.setNivelDesconto(rs.getInt("nivelDesconto"));
                descontoTemp.setPorcentagemDesconto(rs.getInt("porcentagemDesconto"));
                descontoTemp.setValorRequisitado(rs.getString("valorRequisitado"));

                valorRequisitado = new BigDecimal(descontoTemp.getValorRequisitado());

                if (valorTotal.compareTo(valorRequisitado) >= 0) {
                    nivel = descontoTemp.getNivelDesconto();
                }
            }
        } catch (SQLException sqlE) {
            System.err.println(Data.getData() + ": " + sqlE.getMessage());
        }
        return nivel;
    }

    public List<Desconto> lista() {
        return new ArrayList<Desconto>();
    }
}