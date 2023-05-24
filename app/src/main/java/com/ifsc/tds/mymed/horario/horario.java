package com.ifsc.tds.mymed.horario;

import com.ifsc.tds.mymed.remedio.Remedio;

import java.time.LocalTime;

public class horario {
    String id;
    LocalTime horarioSelecionado;
    Remedio remedio;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public horario(){}

    //NECESSÀRIO GET E SET
    public Remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(Remedio remedio) {this.remedio = remedio; }

    public void remedio(Remedio remedio) {
        this.remedio = remedio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getHorarioSelecionado() {
        return horarioSelecionado;
    }

    public void setHorarioSelecionado(LocalTime horarioSelecionado) {this.horarioSelecionado = horarioSelecionado;}
}
