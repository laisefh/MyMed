package com.ifsc.tds.mymed.usuario;

import java.util.Date;

public class usuario {
    String id;
    String nome;
    Date dataNascimento;
    String email;
    String senha;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public usuario(){}

    //NECESSÀRIO GET E SET
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public usuario(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento){this.dataNascimento = dataNascimento;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){this.email = email;}

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha){this.senha = senha;}
}
