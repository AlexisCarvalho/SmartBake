package model;

// Codigo não autoral

import model.bean.Funcionario;
import util.Data;
import util.ErrosEmArquivo;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Hashing {

    ErrosEmArquivo errosEmArquivo;

    public Hashing() {
        errosEmArquivo = new ErrosEmArquivo();
        errosEmArquivo.ativarSaidaErrosArquivo();
    }

    public boolean autenticacaoSenha(String senhaFornecida, Funcionario funcionario) {

        String senhaHashArmazenado = funcionario.getHashSenha();
        String saltArmazenado = funcionario.getSalt();

        try {
            // Decodifique o salt e o hash da senha armazenados a partir de Base64
            byte[] salt = Base64.getDecoder().decode(saltArmazenado);
            byte[] senhaHash = Base64.getDecoder().decode(senhaHashArmazenado);

            // Crie uma instância do PBEKeySpec com a senha fornecida e o salt
            KeySpec spec = new PBEKeySpec(senhaFornecida.toCharArray(), salt, 10000, 256);

            // Use o algoritmo de chave secreta para calcular o hash da senha fornecida
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] senhaHashFornecida = factory.generateSecret(spec).getEncoded();

            // Compare o hash calculado com o hash armazenado
            return MessageDigest.isEqual(senhaHash, senhaHashFornecida);

        } catch (NoSuchAlgorithmException e) {
            System.err.println(Data.getData() + ": " + "Algoritmo de hashing não encontrado.");
        } catch (InvalidKeySpecException ikse) {
            System.err.println(Data.getData() + ": " + "KeySpec invalido");
        }
        return false;
    }

    public Funcionario gerarHashSenha(String senha) {
        Funcionario funcionario = new Funcionario();
        try {
            // Gere um salt aleatório
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Configurações para o algoritmo de hashing (SHA-256 com iteracoes)
            int iterations = 10000;
            int keyLength = 256;

            // Crie uma instância do PBEKeySpec com a senha, salt e configurações
            KeySpec spec = new PBEKeySpec(senha.toCharArray(), salt, iterations, keyLength);

            // Use o algoritmo de chave secreta para calcular o hash da senha
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] senhaHash = factory.generateSecret(spec).getEncoded();

            // Converta o salt e o hash em representações Base64
            String saltBase64 = Base64.getEncoder().encodeToString(salt);
            String senhaHashBase64 = Base64.getEncoder().encodeToString(senhaHash);

            funcionario.setSalt(saltBase64);
            funcionario.setHashSenha(senhaHashBase64);

        } catch (NoSuchAlgorithmException e) {
            System.err.println(Data.getData() + ": " + "Erro: Algoritmo de hashing não encontrado.");
        } catch (InvalidKeySpecException ikse) {
            System.err.println(Data.getData() + ": " + "KeySpec invalido");
        }
        return funcionario;
    }
}