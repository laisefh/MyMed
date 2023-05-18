package com.ifsc.tds.mymed.horario;

import com.ifsc.tds.mymed.remedio.remedio;

import java.sql.Time;

public class horario {
    String id;
    Time horarioSelecionado;
    remedio remedio;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public horario(){}

    //NECESSÀRIO GET E SET
    public remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(remedio remedio) {this.remedio = remedio; }

    public void remedio(remedio remedio) {
        this.remedio = remedio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Time getHorarioSelecionado() {
        return horarioSelecionado;
    }

    public void setHorarioSelecionado(Time horarioSelecionado) {this.horarioSelecionado = horarioSelecionado;}
}
