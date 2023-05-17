package com.ifsc.tds.mymed.livro;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LivroViewModel extends ViewModel {
    private MutableLiveData<List<Livro>> listaLivros;
    private MutableLiveData<Livro> livro;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference listaLivrosRef = database.getReference("lista-livros");

    public LiveData<List<Livro>> getListaLivros() {
        if (listaLivros == null) {
            listaLivros = new MutableLiveData<List<Livro>>();
            carregarLivros();
        }
        return listaLivros;
    }

    private void carregarLivros() {
        listaLivrosRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Livro l = snapshot.getValue(Livro.class);
                //listaLivros.getValue().clear();
                List<Livro> lista = new ArrayList<Livro>();
                lista.clear();
                for (DataSnapshot dados: snapshot.getChildren()) {
                    Livro novoLivro = dados.getValue(Livro.class);
                    novoLivro.setId(dados.getKey());
                    lista.add(novoLivro);
                    Log.d("MYMED2023", "Livro adicionado: " + novoLivro.nome);
                }
                listaLivros.postValue(lista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("MYMED2023", "Failed to read value.", error.toException());
            }
        });
    }

    public LiveData<Livro> getLivro(String id) {
        if (livro == null) {
            livro = new MutableLiveData<Livro>();
            carregarLivro(id);
        }
        return livro;
    }

    private void carregarLivro(String id){
        DatabaseReference livroRef = listaLivrosRef.child(id);
        livroRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Livro novoLivro = snapshot.getValue(Livro.class);
                novoLivro.setId(snapshot.getKey());
                livro.postValue(novoLivro);
                Log.d("MYMED2023", "Livro adicionado: " + novoLivro.nome);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("MYMED2023", "Failed to read value.", error.toException());
            }
        });

    }

    public void insertLivro(String nome){
        Livro l = new Livro(nome);
        listaLivrosRef.push().setValue(l);
    }

    public void deleteLivro(String id){
        listaLivrosRef.child(id).removeValue();
        //Opcional, adicionar retorno
        listaLivrosRef.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Log.d("MYMED2023", "Livro removido: " + id);
                else
                    Log.d("MYMED2023", "Não foi possível remover o livro: " + id);
            }
        });
    }

    public void update(Livro livro){
        listaLivrosRef.child(livro.getId()).setValue(livro);
        //Opcional, adicionar retorno (Versão resumida com lambda)
        listaLivrosRef.child(livro.getId()).setValue(livro).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Log.d("MYMED2023", "Livro editado: " + livro.getId());
            else
                Log.d("MYMED2023", "Não foi editar o livro: " + livro.getId());
        });
    }

    //USAR APENAS NA FASE TESTE
    public Task<Void> deleteAll(){
        //Remove todos os valores a partir da raiz
        return listaLivrosRef.removeValue();
    }
}
