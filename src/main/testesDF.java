package main;

import controller.Analise;
import util.DbConnect;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class testesDF {
    public static void main(String[] args) {
        Analise analise = new Analise();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String banco = "";
        int resposta = 0;
        
        analise.openConn();

        analise.gerarResultados(analise.getDadosBanco());

        System.out.println("Relatorio gerado em: " + analise.getData());
        System.out.println("Numero de dados coletados: " + analise.getNumeroDeDadosColetados());
        System.out.println("xMax: " + analise.getMaiorVariavel());
        System.out.println("xMin: " + analise.getMenorVariavel());
        System.out.println("log: " + analise.getLog());
        System.out.println("Classes: " + analise.getClasses());
        System.out.println("Amplitude: " + analise.getAmplitudeAmostral());
        System.out.println("Amplitude Intervalos: " + analise.getAmplitudeIntervalos());

        BigDecimal[] limitesInferiores = analise.getLimitesInferiores();
        BigDecimal[] limitesSuperiores = analise.getLimitesSuperiores();
        BigDecimal[] frequenciasAbsolutas = analise.getFrequenciasAbsolutas();
        BigDecimal[] pontosMedios = analise.getPontosMedios();
        BigDecimal[] frequenciasRelativas = analise.getFrequenciasRelativas();
        BigDecimal[] frequenciaRelativaPorcentagem = analise.getFrequenciasRelativasPorcentagem();
        BigDecimal[] frequenciasAcumuladas = analise.getFrequenciasAcumuladas();
        BigDecimal[] frequenciasAcumuladasPorcentagem = analise.getFrequenciasAcumuladasPorcentagem();

        System.out.println(" li      Li   fi     xi     fri    fri%    fac    fac%");

        for (int smtr = 0; smtr < (analise.getTabela().length); smtr++) {
            System.out.println(
                    limitesInferiores[smtr] + " --| " + limitesSuperiores[smtr] +
                            " |" + frequenciasAbsolutas[smtr] + "| " +
                            " |" + pontosMedios[smtr] + "| " +
                            " |" + frequenciasRelativas[smtr] + "| " +
                            " |" + frequenciaRelativaPorcentagem[smtr] + "| " +
                            " |" + frequenciasAcumuladas[smtr] + "| " +
                            " |" + frequenciasAcumuladasPorcentagem[smtr] + "| "
            );
        }
        System.out.println("Deseja registrar o relatorio no banco? 1/S 2/N");
        resposta = scanner.nextInt();

        if (resposta == 1) {
            analise.salvarNoBanco();
        }
        
        try {
        	scanner.close();
			analise.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}