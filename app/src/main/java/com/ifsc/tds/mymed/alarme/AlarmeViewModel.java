package com.ifsc.tds.mymed.alarme;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifsc.tds.mymed.livro.Livro;
import com.ifsc.tds.mymed.remedio.Remedio;

import java.util.ArrayList;
import java.util.List;


public class AlarmeViewModel extends ViewModel{
    private MutableLiveData<List<Alarme>> listaAlarmes;
    private MutableLiveData<Alarme> alarme;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference listaAlarmesRef = database.getReference("lista-alarmes");

    public LiveData<List<Alarme>> getListaAlarmes() {
        if (listaAlarmes == null) {
            listaAlarmes = new MutableLiveData<List<Alarme>>();
            carregarAlarmes();
        }
        return listaAlarmes;
    }

    private void carregarAlarmes() {
        listaAlarmesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Alarme a = snapshot.getValue(Alarme.class);
                //listaAlarmes.getValue().clear();
                List<Alarme> lista = new ArrayList<Alarme>();
                lista.clear();
                for (DataSnapshot dados: snapshot.getChildren()) {
                    Alarme novoAlarme = dados.getValue(Alarme.class);
                    novoAlarme.setId(dados.getKey());
                    lista.add(novoAlarme);
                    Log.d("MYMED2023", "Alarme adicionado: " + novoAlarme.id);
                }
                listaAlarmes.postValue(lista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("MYMED2023", "Failed to read value.", error.toException());
            }
        });
    }

    public LiveData<Alarme> getAlarme(String id) {
        if (alarme == null) {
            alarme = new MutableLiveData<Alarme>();
            carregarAlarmes(id);
        }
        return alarme;
    }

    private void carregarAlarmes(String id){
        DatabaseReference alarmeRef = listaAlarmesRef.child(id);
        alarmeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Alarme novoAlarme = snapshot.getValue(Alarme.class);
                novoAlarme.setId(snapshot.getKey());
                alarme.postValue(novoAlarme);
                Log.d("MYMED2023", "Alarme adicionado: " + novoAlarme.remedio);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("MYMED2023", "Failed to read value.", error.toException());
            }
        });

    }

    public void insertAlarme(Remedio remedio){
        Alarme a = new Alarme(remedio);
       // a.setDatafinal();
        a.setHorario(null);
        listaAlarmesRef.push().setValue(a);
    }

    public void deleteAlarme(String id){
        listaAlarmesRef.child(id).removeValue();
        //Opcional, adicionar retorno
        listaAlarmesRef.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Log.d("MYMED2023", "Alarme removido: " + id);
                else
                    Log.d("MYMED2023", "Não foi possível remover o alarme: " + id);
            }
        });
    }

    public void update(Alarme alarme1){
        listaAlarmesRef.child(alarme1.getId()).setValue(alarme1);
        //Opcional, adicionar retorno (Versão resumida com lambda)
        listaAlarmesRef.child(alarme1.getId()).setValue(alarme1).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Log.d("MYMED2023", "Alarme editado: " + alarme.getValue());
            else
                Log.d("MYMED2023", "Não foi editar o alarme: " + alarme.getValue());
        });
    }

    //USAR APENAS NA FASE TESTE
    public Task<Void> deleteAll(){
        //Remove todos os valores a partir da raiz
        return listaAlarmesRef.removeValue();
    }
}