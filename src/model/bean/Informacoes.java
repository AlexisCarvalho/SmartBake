package model.bean;

import java.math.BigDecimal;

public class Informacoes {

    private BigDecimal quantiVariaveis, max, min, log, classes, amplitude, amplitudeIntervalos;
    private BigDecimal[][] tabela;
    private String data;

    public Informacoes() {
        data = "";
        quantiVariaveis = new BigDecimal("0");
        max = new BigDecimal("0");
        min = new BigDecimal("0");
        log = new BigDecimal("0");
        classes = new BigDecimal("0");
        amplitude = new BigDecimal("0");
        amplitudeIntervalos = new BigDecimal("0");
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BigDecimal getNumeroDeDadosColetados() {
        return quantiVariaveis;
    }

    public void setNumeroDeDadosColetados(BigDecimal quantiVariaveis) {
        this.quantiVariaveis = quantiVariaveis;
    }

    public BigDecimal getMaiorVariavel() {
        return max;
    }

    public void setMaiorVariavel(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMenorVariavel() {
        return min;
    }

    public void setMenorVariavel(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getLog() {
        return log;
    }

    public void setLog(BigDecimal log) {
        this.log = log;
    }

    public BigDecimal getClasses() {
        return classes;
    }

    public void setClasses(BigDecimal classes) {
        this.classes = classes;
    }

    public BigDecimal getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(BigDecimal amplitude) {
        this.amplitude = amplitude;
    }

    public BigDecimal getAmplitudeIntervalos() {
        return amplitudeIntervalos;
    }

    public void setAmplitudeIntervalos(BigDecimal amplitudeIntervalos) {
        this.amplitudeIntervalos = amplitudeIntervalos;
    }

    public BigDecimal[][] getTabela() {
        return tabela;
    }

    public void setTabela(BigDecimal[][] tabela) {
        this.tabela = tabela;
    }
}
