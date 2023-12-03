package main;

import controller.FuncaoCaixa;
import controller.FuncaoEstoque;
import controller.FuncaoGerente;
import controller.FuncaoLogin;
import model.DistribuicaoDeFrequencia;
import model.bean.Produto;
import util.Data;
import util.ErrosEmArquivo;
import util.RetornarTroco;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Testes {
    public static void main(String[] args) {
        FuncaoCaixa fc = new FuncaoCaixa();
        FuncaoGerente gr = new FuncaoGerente();
        FuncaoEstoque es = new FuncaoEstoque();
        FuncaoLogin lg = new FuncaoLogin();
        fc.openConn();
        gr.openConn();
        es.openConn();
        lg.openConn();
        
        DistribuicaoDeFrequencia df = new DistribuicaoDeFrequencia();
        Scanner sc = new Scanner(System.in);
        String nomeCliente = "";

        ErrosEmArquivo er = new ErrosEmArquivo();
        er.ativarSaidaErrosArquivo();

        int[] inteiros = {1, 2, 2, 5, 6, 7, 8, 9, 4, 6, 3, 1, 8, 6, 9, 3};

        List<Integer> j = new ArrayList<>();

        for (int b : inteiros) {
            j.add(b);
        }

        try {
            gr.cadastrarFuncionario("Alexis", "adm001", 1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        try {
            gr.cadastrarFuncionario("Gabriel", "adm002", 2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {

            lg.autenticar("Teste", "testando123");
            System.out.println("Login feito com sucesso");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            gr.cadastrarProduto("0.33", "Pão", "Um pão normal");
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }

        try {
            gr.cadastrarProduto("4.2", "Pao Especial", "Um pao normal com um nome especial e um preço elevado");
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }

        try {
            gr.cadastrarProduto( "2.25", "Pao de Queijo", "O que o nome dis");
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }

        try {
            gr.cadastrarProduto("6.75","Coquinha", "O que o nome dis");
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }
        try {
            gr.cadastrarProduto("9.99","queijo branco", "O que o nome dis");
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }
        try {
            gr.cadastrarProduto("7.50","hamburgão", "O que o nome dis");
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }

        String testevirgu = "2,5";

        testevirgu = testevirgu.replaceAll(",", ".");

        System.out.println(testevirgu);

        System.out.println(gr.calcularGastosCliente("12332145688"));

        try {
            gr.cadastrarProduto("testando", "O que o nome dis", "0.40");
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }

        List<Produto> produtos = es.listarProdutosEstoque();

        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getIdProduto() + " NOME: " + produto.getNomeProduto() + " PRECO: " + produto.getValorProduto());
        }

        try {
            gr.cadastrarPlanoFidelidade(3, "50");
        } catch (IllegalArgumentException i) {
            System.err.println(Data.getData() + ": " + i.getMessage());
        }
        try {
            gr.cadastrarPlanoFidelidade(6, "100");
        } catch (IllegalArgumentException i) {
            System.err.println(Data.getData() + ": " + i.getMessage());
        }
        try {
            gr.cadastrarPlanoFidelidade(9, "150");
        } catch (IllegalArgumentException i) {
            System.err.println(Data.getData() + ": " + i.getMessage());
        }
        try {
            gr.cadastrarPlanoFidelidade(15, "200");
        } catch (IllegalArgumentException i) {
            System.err.println(Data.getData() + ": " + i.getMessage());
        }

        try {
            gr.cadastrarCliente("12332145688", "Gabriel Alarcon", 1);
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }

        try {
            gr.cadastrarCliente("12332145691", "EuMermo", 0);
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }

        try {
            gr.atualizarCliente("12332145688", "Gabriel Alarcon", 2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            gr.deletarCliente("12332145678");
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }

        gr.atualizarDescontoDeTodos();

        System.out.println("Informe o cpf: ");
        String cpf = sc.next();
        try {
            nomeCliente = fc.setCliente(cpf);
        } catch (IllegalArgumentException e) {
            System.err.println(Data.getData() + ": " + e.getMessage());
        }
        System.out.println(nomeCliente);

        for (int a = 0; a < 3; a++) {
            System.out.println("Insira o codigo do produto: ");
            int codigo = sc.nextInt();
            System.out.println("Insira a quantidade: ");
            int quantidade = sc.nextInt();
            try {
                fc.registrarProduto(codigo, quantidade);
                System.out.println(fc.valorDescontado());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Deseja cancelar algum item? ");
        boolean sn = sc.nextBoolean();
        if (sn) {
            System.out.println("Digite o indice: ");
            byte indice = sc.nextByte();
            fc.cancelarItem(indice);
        }

        fc.valorAReceber(true);

        do {
            System.out.println("Valor a pagar: " + fc.valorAReceber(false));
            System.out.println("Insira o valor pago: ");
            try {
                fc.receber(sc.next());
            } catch (RetornarTroco tr) {
                System.out.println("Retornar ao cliente: " + fc.valorTroco());
            }
        } while (fc.valorAReceber());

        sc.close();
        fc.sendToDB();
        System.out.println(nomeCliente + " Obrigado pela Compra");
        
    }
}