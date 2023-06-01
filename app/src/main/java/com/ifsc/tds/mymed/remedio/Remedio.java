package com.ifsc.tds.mymed.remedio;

import com.ifsc.tds.mymed.data.model.Usuario;

public class Remedio {
    String id;
    String nome;
    String anotacoes;
    String horaInicial;
    String intervaloHoras;
    int tipoFrequencia;


    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public Remedio(){}

    public Remedio(String id, String nome, String horaInicial, String anotacoes, String intervaloHoras, int tipoFrequencia) {
        this.id = id;
        this.nome = nome;
        this.horaInicial = horaInicial;
        this.anotacoes = anotacoes;
        this.intervaloHoras = intervaloHoras;
        this.tipoFrequencia = tipoFrequencia;
    }

    public Remedio(String nome, String horaInicial, String anotacoes, String intervaloHoras, int tipoFrequencia) {
        this.nome = nome;
        this.horaInicial = horaInicial;
        this.anotacoes = anotacoes;
        this.intervaloHoras = intervaloHoras;
        this.tipoFrequencia = tipoFrequencia;
    }


    //NECESSÀRIO GET E SET
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setIntervaloHoras(String intervaloHoras) {
        this.intervaloHoras = intervaloHoras;
    }

    public String getIntervaloHoras() {
        return intervaloHoras;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
