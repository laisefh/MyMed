package com.ifsc.tds.mymed.alarme;


import com.ifsc.tds.mymed.remedio.remedio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Alarme {
    String id;
    LocalDate dataInicial;
    LocalDate datafinal;
    LocalTime horario;
    remedio remedio;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public Alarme(){}

    //NECESSÀRIO GET E SET

    public Alarme(remedio remedio) {
        this.remedio = remedio;
    }

    public remedio getRemedio() {
        return getRemedio();
    }

    public void setRemedio(remedio remedio){this.remedio = remedio;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial){this.dataInicial = dataInicial;}

    public LocalDate getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(LocalDate datafinal){this.datafinal = datafinal;}

    public LocalTime getHorario() {return horario;}

    public void setHorario(LocalTime horario){this.horario = horario;}

}
