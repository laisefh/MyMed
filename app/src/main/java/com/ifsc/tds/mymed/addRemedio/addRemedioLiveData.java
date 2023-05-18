package com.ifsc.tds.mymed.addRemedio;public class addRemedioLiveData {

public class addRemedio {
    String id;
    String nome;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public Livro(){}

    //NECESSÀRIO GET E SET
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Livro(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
}
