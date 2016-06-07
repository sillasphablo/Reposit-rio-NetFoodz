package main.java.dominio;

/**
 * Created by John on 05/05/2016.
 */
public class Usuario {
    private Pessoa pessoa;
    private String login;
    private String senha;

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String username) {
        this.login = username;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }



}
