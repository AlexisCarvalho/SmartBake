package model;

import model.bean.Informacoes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public class DistribuicaoDeFrequencia {

    private BigDecimal multiplicand, totalFrequenciaRelativa, totalFrequencia, totalFrequenciaRelativaPorcentagem;
    private BigDecimal quantiVariaveis, limiteInferior, limiteSuperior, max, min, frequenciaRelativaPorcentagem;
    private BigDecimal log, classes, amplitude, amplitudeIntervalos, frequenciaRelativa;
    private BigDecimal[][] tabela;
    private Informacoes informacoes;

    public DistribuicaoDeFrequencia() {
        totalFrequencia = new BigDecimal("0");
        totalFrequenciaRelativa = new BigDecimal("0");
        totalFrequenciaRelativaPorcentagem = new BigDecimal("0");
        multiplicand = new BigDecimal("3.322");
        quantiVariaveis = new BigDecimal("0");
        informacoes = new Informacoes();
    }

    public Informacoes getInformacoes() {
        informacoes.setAmplitude(amplitude);
        informacoes.setAmplitudeIntervalos(amplitudeIntervalos);
        informacoes.setClasses(classes);
        informacoes.setLog(log);
        informacoes.setMaiorVariavel(max);
        informacoes.setMenorVariavel(min);
        informacoes.setNumeroDeDadosColetados(quantiVariaveis);
        informacoes.setTabela(tabela);
        return informacoes;
    }

    public void efetuarCalculos(List<BigDecimal> lista) {

        Collections.sort(lista);

        quantiVariaveis = quantiVariaveis.add(new BigDecimal(String.valueOf(lista.size())));
        log = new BigDecimal(String.valueOf(Math.log10(quantiVariaveis.doubleValue())), new MathContext(3));

        min = lista.get(0);
        max = lista.get((lista.size()) - 1);

        classes = log.multiply(multiplicand).add(BigDecimal.ONE).round(new MathContext(2));
        if(classes.compareTo(BigDecimal.TEN) < 0){
            classes = classes.round(new MathContext(1));
        }

        if (classes.compareTo(new BigDecimal("15")) > 0) {
            classes = new BigDecimal("15");
        } else if (classes.compareTo(new BigDecimal("5")) < 0) {
            classes = new BigDecimal("5");
        }

        limiteInferior = min;

        amplitude = max.subtract(min);
        amplitudeIntervalos = amplitude.divide(classes, new MathContext(6));

        if (amplitudeIntervalos.compareTo(BigDecimal.ONE) > 0) {
            amplitudeIntervalos = new BigDecimal(String.valueOf(amplitudeIntervalos.intValue() + 1)); // Arredondamento dos intervalos, versão inteira mais 1
        } else {
            amplitudeIntervalos = amplitudeIntervalos.round(new MathContext(2, RoundingMode.CEILING)); // Se o numero for menor que 1 se limitara a duas casas decimais, sempre arredondando para o valor mais alto
        }

        BigDecimal testeIntervalo = new BigDecimal(String.valueOf(min));
        for (double smtr = 0; smtr < classes.intValue(); smtr++) {
            testeIntervalo = testeIntervalo.add(amplitudeIntervalos);
        }
        if (testeIntervalo.compareTo(max) == 0) {
            amplitudeIntervalos = amplitudeIntervalos.add(new BigDecimal("0.01")); // Compara se mesmo após o arredondamento para dezena mais proxima o valor bate exatamente
        }                                                                               // com o teto da ultima classe da tabela (O que acaba inutilizando as variaveis neste teto)

        BigDecimal frequenciaAbsoluta = new BigDecimal("0");
        tabela = new BigDecimal[classes.intValue()][8];

        for (int smtr = 0; smtr < classes.intValue(); smtr++) {
            limiteSuperior = limiteInferior.add(amplitudeIntervalos);
            for (BigDecimal a : lista) {
                if (a.compareTo(limiteInferior) >= 0 && a.compareTo(limiteSuperior) < 0) {
                    frequenciaAbsoluta = frequenciaAbsoluta.add(BigDecimal.ONE);
                }
            }

            frequenciaRelativa = frequenciaAbsoluta.divide(new BigDecimal(String.valueOf(quantiVariaveis)), new MathContext(3));

            totalFrequencia = totalFrequencia.add(frequenciaAbsoluta);
            totalFrequenciaRelativa = totalFrequenciaRelativa.add(frequenciaRelativa);

            BigDecimal pontoMedio = new BigDecimal("0");
            pontoMedio = pontoMedio.add(limiteInferior).add(limiteSuperior).divide(BigDecimal.TWO, new MathContext(5));

            tabela[smtr][0] = limiteInferior;
            tabela[smtr][1] = limiteSuperior;
            tabela[smtr][2] = frequenciaAbsoluta;
            tabela[smtr][3] = pontoMedio;
            tabela[smtr][4] = frequenciaRelativa;
            tabela[smtr][6] = totalFrequencia;

            frequenciaAbsoluta = new BigDecimal("0");
            limiteInferior = limiteSuperior;
        }

        BigDecimal diferenca = new BigDecimal(String.valueOf(BigDecimal.ONE.subtract(totalFrequenciaRelativa)));
        int posicaoCompensacao = 0;
        BigDecimal valorACompensar = new BigDecimal("0");

        if (diferenca.compareTo(BigDecimal.ZERO) != 0) {
            for (int smtr = 0; smtr < classes.intValue(); smtr++) {
                if (tabela[smtr][4].compareTo(valorACompensar) > 0) {
                    valorACompensar = tabela[smtr][4];
                    posicaoCompensacao = smtr;
                }
            }
            tabela[posicaoCompensacao][4] = tabela[posicaoCompensacao][4].add(diferenca);
        }

        for (int smtr = 0; smtr < classes.intValue(); smtr++) {
            tabela[smtr][7] = new BigDecimal("0");
            frequenciaRelativaPorcentagem = tabela[smtr][4].multiply(new BigDecimal("100"));
            tabela[smtr][5] = frequenciaRelativaPorcentagem;
            if (smtr > 0) {
                totalFrequenciaRelativaPorcentagem = tabela[smtr - 1][7].add(tabela[(smtr)][5]);
            } else {
                totalFrequenciaRelativaPorcentagem = tabela[smtr][7].add(tabela[(smtr)][5]);
            }
            tabela[smtr][7] = totalFrequenciaRelativaPorcentagem;
        }

        totalFrequenciaRelativa = new BigDecimal("0");
        for (int smtr = 0; smtr < classes.intValue(); smtr++) {
            totalFrequenciaRelativa = totalFrequenciaRelativa.add(tabela[smtr][4]);
        }
        for (int smtr = 0; smtr < classes.intValue(); smtr++) {
            totalFrequenciaRelativaPorcentagem = totalFrequenciaRelativaPorcentagem.add(tabela[smtr][5]);
        }
    }
}