package com.ifsc.tds.mymed.data.model;

public class Usuario {
    private String nome;
    private String email;
    private String dataNascimento;

    public Usuario() {
    }

    public Usuario(String nome, String email, String dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}
