package com.ifsc.tds.mymed.alarme;


import com.ifsc.tds.mymed.remedio.Remedio;

public class Alarme {
    String id;
    String horario;
    Remedio remedio;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public Alarme(){}

    public Alarme(String id, String horario, Remedio remedio) {
        this.id = id;
        this.horario = horario;
        this.remedio = remedio;
    }
    //NECESSÀRIO GET E SET

    public Alarme(Remedio remedio) {
        this.remedio = remedio;
    }

    public Remedio getRemedio() {
        return getRemedio();
    }

    public void setRemedio(Remedio remedio){this.remedio = remedio;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHorario() {return horario;}

    public void setHorario(String horario){this.horario = horario;}

}
