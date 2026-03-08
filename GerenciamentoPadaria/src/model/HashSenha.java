package model;

import util.Data;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class HashSenha {

    public HashSenha() {

    }

    public void autenticacaoSenha(String senhaFornecida, String senhaHashArmazenado, String saltArmazenado) {

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
            boolean autenticado = MessageDigest.isEqual(senhaHash, senhaHashFornecida);

            if (autenticado) {
                System.out.println("Senha autenticada com sucesso.");
            } else {
                System.out.println("Senha incorreta.");
            }
        } catch (NoSuchAlgorithmException e) {
            System.err.println(Data.getData() + ": " + "Algoritmo de hashing não encontrado.");
        } catch (InvalidKeySpecException ikse) {
            System.err.println(Data.getData() + ": " + "KeySpec invalido");
        }
    }

    public void gerarHashSenha(String senha) {
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

            System.out.println("Senha: " + senha);
            System.out.println("Salt: " + saltBase64);
            System.out.println("Hash da Senha: " + senhaHashBase64);

            // Armazene o saltBase64 e senhaHashBase64 no banco de dados

        } catch (NoSuchAlgorithmException e) {
            System.err.println(Data.getData() + ": " + "Erro: Algoritmo de hashing não encontrado.");
        } catch (InvalidKeySpecException ikse) {
            System.err.println(Data.getData() + ": " + "KeySpec invalido");
        }
    }
}