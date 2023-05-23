package com.ifsc.tds.mymed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ifsc.tds.mymed.alarme.AlarmeViewModel;
import com.ifsc.tds.mymed.livro.Livro;
import com.ifsc.tds.mymed.livro.LivroViewModel;
import com.ifsc.tds.mymed.remedio.remedio;

import java.util.ArrayList;
import java.util.List;

public class
MainActivity extends AppCompatActivity {
    List<Livro> lista;
    Livro livro;
    LivroViewModel livroViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* APENAS PARA TESTES
        livroViewModel = new ViewModelProvider(this).get(LivroViewModel.class);
        //Limpa o banco de dados e inicia os testes
        livroViewModel.deleteAll().addOnCompleteListener(task -> {
            rodarTestes();
        });*/

    }

    public void rodarTestes(){
        livroViewModel.insertLivro("Teste1");
        livroViewModel.insertLivro("Teste2");
        livroViewModel.insertLivro("Teste3");

        livroViewModel.getListaLivros().observe(this, new Observer<List<Livro>>() {
            @Override
            public void onChanged(List<Livro> livros) {
                lista = livros;
                Log.d("MYMED2023", "Lista atualizada: " + lista);
                //É AQUI ONDE FICARIA O CÓDIGO DE ATUALIZAR UI
                //EXEMPLO: ATUALIZAR A LISTA (notifydatasetchanged talvez)

                //Exemplo de leitura de um objeto e operações CRUD
                lerLivro();
                editarLivro();
                apagarLivro();
            }
        });
    }

    public void lerLivro(){
        String id = lista.get(0).getId();
        livroViewModel.getLivro(id).observe(this, new Observer<Livro>() {
            @Override
            public void onChanged(Livro livroLido) {
                livro = livroLido;
                Log.d("MYMED2023", "Livro lido" + livro.getNome());
            }
        });
    }

    public void editarLivro(){
        Livro livro = lista.get(0);
        livro.setNome("Android Firebase CRUD");
        livroViewModel.update(livro);
    }

    public void apagarLivro(){
        Livro livro = lista.get(2);
        livroViewModel.deleteLivro(livro.getId());
    }
}
