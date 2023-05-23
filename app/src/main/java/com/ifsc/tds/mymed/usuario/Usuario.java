package com.ifsc.tds.mymed.usuario;

import java.util.Date;

public class Usuario {
    String id;
    String nome;
    String dataNascimento;
    String email;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public Usuario(){}

    //NECESSÀRIO GET E SET
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario(String nome, String dataNascimento, String email) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento){this.dataNascimento = dataNascimento;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){this.email = email;}
}
