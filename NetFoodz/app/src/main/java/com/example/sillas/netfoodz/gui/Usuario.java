package com.example.sillas.netfoodz.gui;

/**
 * Created by John on 05/05/2016.
 */
public class Usuario {
    private Pessoa pessoa;
    private String username;
    private String senha;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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
