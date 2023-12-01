package main;

import controller.Analise;

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
        
        while (resposta < 1 || resposta > 3) {
            System.out.println("Deseja registrar dados no banco? \n1) Sim, gerar dados aleatorios\n2) Sim, irei registrar dados manualmente\n3) Nao, o banco ja inclui os dados necessarios");
            resposta = scanner.nextInt();
        }

        switch (resposta) {
            case 1:
                System.out.println("Quantas variaveis deseja adicionar? (Inteiro)");
                int variaveisNovas = scanner.nextInt();
                System.out.println("Os numeros gerados respeitarao a escala de 0 a ? (Real)");
                double escala = scanner.nextDouble();

                for (int a = 0; a < variaveisNovas; a++) {
                    analise.adicionarVariavelAoBanco(a,String.valueOf(new BigDecimal(String.valueOf(random.nextDouble(escala)), new MathContext(3))));
                }
                break;

            case 2:

                String variavel = "";
                int indice = 1;
                System.out.println("Digite as variaveis uma por uma, quando derminar digite E");

                while (!variavel.equals("e") && !variavel.equals("E")) {
                    System.out.print(indice + "ª: ");
                    variavel = scanner.next();

                    if (!variavel.equals("e") && !variavel.equals("E")) {
                        analise.adicionarVariavelAoBanco(indice, variavel);
                        indice++;
                    }
                }

                break;

            default:

                break;
        }

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