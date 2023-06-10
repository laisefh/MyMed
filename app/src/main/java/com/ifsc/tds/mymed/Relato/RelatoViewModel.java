package com.ifsc.tds.mymed.Relato;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifsc.tds.mymed.remedio.Remedio;

import java.util.ArrayList;
import java.util.List;

public class RelatoViewModel extends ViewModel {
    private MutableLiveData<List<Relato>> listaRelatos;
    private MutableLiveData<Relato> relato;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference listaRelatoRef;

    private DatabaseReference getCurrentRef(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uuid = auth.getUid();
        return database.getReference("usuarios").child(uuid).child("lista-relato");
    }

    public LiveData<List<Relato>> getListaRelato() {
        if (listaRelatos == null) {
            listaRelatos = new MutableLiveData<List<Relato>>();
            carregarRelato();
        }
        return listaRelatos;
    }

    private void carregarRelato() {
        listaRelatoRef = getCurrentRef();
        listaRelatoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Livro l = snapshot.getValue(Livro.class);
                //listaLivros.getValue().clear();
                List<Relato> lista = new ArrayList<Relato>();
                lista.clear();
                for (DataSnapshot dados: snapshot.getChildren()) {
                    Relato novo = dados.getValue(Relato.class);
                    novo.setId(dados.getKey());
                    lista.add(novo);
                    Log.d("MYMED2023", "Relato adicionado: " + novo.relato);
                }
                listaRelatos.postValue(lista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("MYMED2023", "Failed to read value.", error.toException());
            }
        });
    }

    public LiveData<Relato> getRelato(String id) {
        if (relato == null) {
            relato = new MutableLiveData<>();
            carregarRelato(id);
        }
        return relato;
    }

    private void carregarRelato(String id){
        //TODO


        DatabaseReference AlarmeRef = listaRelatoRef.child(id);
        AlarmeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Relato novo = snapshot.getValue(Relato.class);
                novo.setId(snapshot.getKey());
                relato.postValue(novo);
                Log.d("MYMED2023", "Relato lido: " + novo.relato);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("MYMED2023", "Failed to read value.", error.toException());
            }
        });
    }

    public void insertRelato(String uuid, String relato){
        Relato novo = new Relato(relato);
        listaRelatoRef = getCurrentRef();
        listaRelatoRef.child(relato).setValue(novo).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("MYMED2023", "Falha ao cadastrar relato", task.getException());
            } else {
                Log.d("MYMED2023", "Remedio criado com sucesso");
            }
        });
    }

    public void deleteRemedio(String id){
        listaRelatoRef = getCurrentRef();
        listaRelatoRef.child(id).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Log.d("MYMED2023", "Relato removido: " + id);
            else
                Log.d("MYMED2023", "Não foi possível remover o relato: " + id);
        });
    }

    public void update(Relato relato){
        listaRelatoRef = getCurrentRef();
        listaRelatoRef.child(relato.getId()).setValue(relato).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Log.d("MYMED2023", "Relato editado: " + relato.getId());
            else
                Log.d("MYMED2023", "Não foi editar o relato: " + relato.getId());
        });
    }

    //USAR APENAS NA FASE TESTE
    public Task<Void> deleteAll(){
        //Remove todos os valores a partir da raiz
        return listaRelatoRef.removeValue();
    }
}
