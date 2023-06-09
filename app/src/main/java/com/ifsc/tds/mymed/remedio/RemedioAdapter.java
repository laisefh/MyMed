package com.ifsc.tds.mymed.remedio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ifsc.tds.mymed.R;

import java.util.List;

public class RemedioAdapter extends RecyclerView.Adapter<RemedioAdapter.RemedioViewHolder>{
    /**********************************************
     *      VIEW HOLDER DA CLASSE PESSOA          *
     **********************************************/
    public static class RemedioViewHolder extends RecyclerView.ViewHolder {
        String key;
        TextView nomeMed;
        TextView anotacoes;
        TextView intervalo;
        Button editar;

        RemedioViewHolder(View itemView) {
            super(itemView);
            nomeMed = (TextView)itemView.findViewById(R.id.textViewRemedio);
            anotacoes = (TextView)itemView.findViewById(R.id.textView4);
            intervalo = (TextView)itemView.findViewById(R.id.textViewHorario);
            editar = (Button) itemView.findViewById(R.id.buttonEditarRemedio);
        }

        public void setRemedioId(String key) { this.key = key;}
    }

    /**********************************************
     *   Lista de Pessoas e Construtor da Classe  *
     **********************************************/

    public List<Remedio> remedios;
    public RemedioAdapter(List<Remedio> remedios){
        this.remedios = remedios;
    }

    /**********************************************
     *      IMPLEMENTAÇÕES DO ADAPTADOR           *
     **********************************************/
    @Override
    public RemedioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_item, parent, false);
        RemedioViewHolder rvh = new RemedioViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RemedioViewHolder viewHolder, int i) {
        viewHolder.nomeMed.setText(remedios.get(i).getNome());
        viewHolder.anotacoes.setText(remedios.get(i).getAnotacoes());
        viewHolder.intervalo.setText(remedios.get(i).getIntervaloHoras());

    }

    @Override
    public int getItemCount() {
        return remedios.size();
    }
}

