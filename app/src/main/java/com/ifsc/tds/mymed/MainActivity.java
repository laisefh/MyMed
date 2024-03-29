package com.ifsc.tds.mymed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.ifsc.tds.mymed.livro.Livro;
import com.ifsc.tds.mymed.livro.LivroViewModel;

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

        //USA A NAVEGAÇÂO PARA MUDAR AS ROTAS NA TOOLBAR
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);


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
