package com.ifsc.tds.mymed.alarme;


import com.ifsc.tds.mymed.remedio.remedio;

import java.sql.Time;
import java.util.Date;

public class alarme{
    String id;
    Date dataInicial;
    Date datafinal;
    Time horario;
    remedio remedio;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public alarme(){}

    //NECESSÀRIO GET E SET

    public alarme(remedio remedio) {
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

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial){this.dataInicial = dataInicial;}

    public Date getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(Date datafinal){this.datafinal = datafinal;}

    public Time getHorario() {return horario;}

    public void setHorario(Time horario){this.horario = horario;}

}
