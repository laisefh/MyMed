package com.ifsc.tds.mymed;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.ifsc.tds.mymed.data.model.Usuario;

import java.time.LocalDate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro extends Fragment {
    private FirebaseAuth mAuth; //acessa os recursos de autenticação do Firebase
    private DatabaseReference mDatabase; //acessa os recursos do banco de dados do Firebase

    private EditText nomeEditText;
    private EditText emailEditText;
    private EditText senhaEditText;
    private EditText diaEditText;
    private EditText mesEditText;
    private EditText anoEditText;
    private RadioButton termosRadioButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth and DataBaseRef to users
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);
        //Configura as referências aos componentes da UI
        nomeEditText = view.findViewById(R.id.editTextNomeCompleto);
        emailEditText = view.findViewById(R.id.editTextCadastroEmail);
        senhaEditText = view.findViewById(R.id.editTextCadastroSenha);
        diaEditText = view.findViewById(R.id.editTextDia);
        mesEditText = view.findViewById(R.id.editTextMes);
        anoEditText = view.findViewById(R.id.editTextAno);
        termosRadioButton = view.findViewById(R.id.radioBtnTermos);
        //Adiciona uma ação ao botão cadastrar
        Button cadastrar = view.findViewById(R.id.buttonCdadastrar);
        cadastrar.setOnClickListener(view1 -> fazerCadastro());
        return view;
    }

    void fazerCadastro() {
        //leitura dos dados
        String nome = nomeEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String senha = senhaEditText.getText().toString();
        int dia = Integer.parseInt(diaEditText.getText().toString());
        int mes = Integer.parseInt(mesEditText.getText().toString());
        int ano = Integer.parseInt(anoEditText.getText().toString());
        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);
        Log.d("MyMed", "Data Nascimento:" + dataNascimento);
        //cria um novo login firebase
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("MyMed", "Falha ao criar novo Login: ", task.getException());
            } else {
                Log.d("MyMed", "Login criado com sucesso");
                //Le o UUI do novo usuário criado
                String uui = task.getResult().getUser().getUid();
                //E cria um novo usuario
                Usuario usuario = new Usuario(nome, email, dataNascimento.toString());
                criarUsuario(uui, usuario);
            }
        });
    }

    void criarUsuario(String uui, Usuario usuario) {
        mDatabase.child(uui).setValue(usuario).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("MyMed", "Falha ao criar dados do usuario: ", task.getException());
            } else {
                Log.d("MyMed", "Usuario criado com sucesso");
                goToLogin();
            }
        });
    }

    void goToLogin() {
        NavController nav = Navigation.findNavController(getView());
        nav.popBackStack();
    }
}