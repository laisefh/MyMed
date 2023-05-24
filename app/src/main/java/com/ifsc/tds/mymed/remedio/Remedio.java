package com.ifsc.tds.mymed.remedio;

import com.ifsc.tds.mymed.usuario.Usuario;

public class Remedio {
    String id;
    String nome;
    String anotacoes;

    String horaInicial;

    int tipoFrequencia;

    //Set diasSelecionados;

    public Remedio(String nome, String anotacoes) {
        this.nome = nome;
        this.anotacoes = anotacoes;
        //this.diasSelecionados = diasSelecionados;
        //this.usuario = usuario;
    }

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public Remedio(){}

    //NECESSÀRIO GET E SET
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Remedio(String nome, String horaInicial, String anotacoes, int tipoFrequencia) {
        this.nome = nome;
        this.horaInicial = horaInicial;
        this.anotacoes = anotacoes;
        this.tipoFrequencia = tipoFrequencia;
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

    //public Set getDiasSelecionados() {return diasSelecionados;}

    //public void setDiasSelecionados(Set diasSelecionados) {this.diasSelecionados = diasSelecionados;}

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public int getTipoFrequencia() {
        return tipoFrequencia;
    }

    public void setTipoFrequencia(int tipoFrequencia) {
        this.tipoFrequencia = tipoFrequencia;
    }
}
