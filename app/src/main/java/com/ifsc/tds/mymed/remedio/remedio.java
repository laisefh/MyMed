package com.ifsc.tds.mymed.remedio;

import com.ifsc.tds.mymed.usuario.usuario;

import java.util.Set;

public class remedio {
    String id;
    String nome;
    String anotacoes;
    Set diasSelecionados;
    usuario usuario;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public remedio(){}

    //NECESSÀRIO GET E SET
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public remedio(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public Set getDiasSelecionados() {return diasSelecionados;}

    public void setDiasSelecionados(Set diasSelecionados) {this.diasSelecionados = diasSelecionados;}

    public usuario getUsuario() { return usuario;}

    public void setUsuario(usuario usuario) {this.usuario = usuario;}
}
