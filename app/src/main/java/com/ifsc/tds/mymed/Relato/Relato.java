package com.ifsc.tds.mymed.Relato;

public class Relato {
    String id;
    String relato;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public Relato(){}

    public Relato(String relato) {
        this.relato = relato;
    }

    //NECESSÀRIO GET E SET
    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
