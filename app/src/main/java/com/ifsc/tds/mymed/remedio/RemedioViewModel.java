package com.ifsc.tds.mymed.remedio;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifsc.tds.mymed.data.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RemedioViewModel extends ViewModel {
    private MutableLiveData<List<Remedio>> listaRemedios;
    private MutableLiveData<Remedio> remedio;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference listaRemediosRef;

    private DatabaseReference getCurrentRef(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uuid = auth.getUid();
        return database.getReference("usuarios").child(uuid).child("lista-remedios");
    }

    public LiveData<List<Remedio>> getListaRemedios() {
        if (listaRemedios == null) {
            listaRemedios = new MutableLiveData<List<Remedio>>();
            carregarRemedios();
        }
        return listaRemedios;
    }

    private void carregarRemedios() {
        listaRemediosRef = getCurrentRef();
        listaRemediosRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Livro l = snapshot.getValue(Livro.class);
                //listaLivros.getValue().clear();
                List<Remedio> lista = new ArrayList<Remedio>();
                lista.clear();
                for (DataSnapshot dados: snapshot.getChildren()) {
                    Remedio novo = dados.getValue(Remedio.class);
                    novo.setId(dados.getKey());
                    lista.add(novo);
                    Log.d("MYMED2023", "Remedio adicionado: " + novo.nome);
                }
                listaRemedios.postValue(lista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("MYMED2023", "Failed to read value.", error.toException());
            }
        });
    }

    public LiveData<Remedio> getRemedio(String id) {
        if (remedio == null) {
            remedio = new MutableLiveData<>();
            carregarRemedio(id);
        }
        return remedio;
    }

    private void carregarRemedio(String id){
        //TODO


        DatabaseReference AlarmeRef = listaRemediosRef.child(id);
        AlarmeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Remedio novo = snapshot.getValue(Remedio.class);
                novo.setId(snapshot.getKey());
                remedio.postValue(novo);
                Log.d("MYMED2023", "Remedio lido: " + novo.nome);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("MYMED2023", "Failed to read value.", error.toException());
            }
        });
    }

    public void insertRemedio(String uuid, String nome, String horaInicial, String intervaloHoras, String anotacoes, int tipoFrequencia){
        Remedio novo = new Remedio(nome, horaInicial, anotacoes, intervaloHoras, tipoFrequencia);
        listaRemediosRef = getCurrentRef();
        listaRemediosRef.push().setValue(novo).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("MYMED2023", "Falha ao cadastrar remedio", task.getException());
            } else {
                Log.d("MYMED2023", "Remedio criado com sucesso");
            }
        });;
    }

    public void deleteRemedio(String id){
        listaRemediosRef = getCurrentRef();
        listaRemediosRef.child(id).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Log.d("MYMED2023", "Remedio removido: " + id);
            else
                Log.d("MYMED2023", "Não foi possível remover o remedio: " + id);
        });
    }

    public void update(Remedio remedio){
        listaRemediosRef = getCurrentRef();
        listaRemediosRef.child(remedio.getId()).setValue(remedio).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Log.d("MYMED2023", "Remedio editado: " + remedio.getId());
            else
                Log.d("MYMED2023", "Não foi editar o remedio: " + remedio.getId());
        });
    }

    //USAR APENAS NA FASE TESTE
    public Task<Void> deleteAll(){
        //Remove todos os valores a partir da raiz
        return listaRemediosRef.removeValue();
    }
}
