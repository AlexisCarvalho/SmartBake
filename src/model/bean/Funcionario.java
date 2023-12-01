package model.bean;

public class Funcionario {

    private int idFuncionario, nivel;
    private String login, hashSenha, salt;

    public Funcionario(int idFuncionario, String login, String hashSenha, String salt, int nivel) {
        this.idFuncionario = idFuncionario;
        this.login = login;
        this.hashSenha = hashSenha;
        this.salt = salt;
        this.nivel = nivel;
    }

    public Funcionario() {
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "registroUsuario{"
                + "idFuncionario=" + idFuncionario
                + ", login=" + login
                + ", hashSenha=" + hashSenha
                + ", salt=" + salt
                + ", nivel=" + nivel + '}';
    }
}