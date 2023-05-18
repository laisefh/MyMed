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
    private LiveData<List<Livro>> listaLivros;
    private LiveData<Livro> livro;
    private LivroRepository livroRepository = new LivroRepository();


    public LiveData<List<Livro>> getListaLivros() {
        if (listaLivros == null)
            listaLivros = livroRepository.getListaLivros();
        return listaLivros;
    }

    public LiveData<Livro> getLivro(String id) {
        if (livro == null) {
            livro = livroRepository.getLivro(id);
        }
        return livro;
    }

    public void insertLivro(String nome){
        Livro l = new Livro(nome);
        livroRepository.insertLivro(l);
    }

    public void deleteLivro(String id){
        //listaLivrosRef.child(id).removeValue();
        //Opcional, adicionar retorno
        livroRepository.deleteLivro(id);
    }

    public void update(Livro livro){
        livroRepository.update(livro);
    }

    //USAR APENAS NA FASE TESTE
    public Task<Void> deleteAll(){
        //Remove todos os valores a partir da raiz
        return livroRepository.deleteAll();
    }
}
