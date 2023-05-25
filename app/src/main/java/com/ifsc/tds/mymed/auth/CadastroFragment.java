package com.ifsc.tds.mymed.auth;

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

import java.time.LocalDate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifsc.tds.mymed.R;
import com.ifsc.tds.mymed.data.model.Usuario;

public class CadastroFragment extends Fragment {
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
        nomeEditText = view.findViewById(R.id.cadastrarNomeEditText);
        emailEditText = view.findViewById(R.id.cadastrarEmailEditText);
        senhaEditText = view.findViewById(R.id.cadastrarSenhalEditText);
        diaEditText = view.findViewById(R.id.cadastrarDiaNascimentoEditText);
        mesEditText = view.findViewById(R.id.cadastrarMesNascimentoEditText);
        anoEditText = view.findViewById(R.id.cadastrarAnoNascimentoEditText);
        termosRadioButton = view.findViewById(R.id.radioBtnTermos);
        //Adiciona uma ação ao botão cadastrar
        Button cadastrar = view.findViewById(R.id.cadastrarUsuarioButton);
        cadastrar.setOnClickListener(view1 -> fazerCadastro());
        Button termos = view.findViewById(R.id.verVerTermosButton);
        termos.setOnClickListener(view12 -> verTermos());
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
        Log.d("MYMED2023", "Data Nascimento:" + dataNascimento);
        //cria um novo login firebase
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("MYMED2023", "Falha ao criar novo Login: ", task.getException());
            } else {
                Log.d("MYMED2023", "Login criado com sucesso");
                //Le o UUI do novo usuário criado
                String uui = task.getResult().getUser().getUid();
                //E cria um novo usuario
                Usuario usuario = new Usuario(nome, dataNascimento.toString(), senha, email);
                criarUsuario(uui, usuario);
            }
        });
    }

    void criarUsuario(String uui, Usuario usuario) {
        mDatabase.child(uui).setValue(usuario).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("MYMED2023", "Falha ao criar dados do usuario: ", task.getException());
            } else {
                Log.d("MYMED2023", "Usuario criado com sucesso");
                goToLogin();
            }
        });
    }

    void goToLogin() {
        NavController nav = Navigation.findNavController(getView());
        nav.popBackStack();
    }

    void verTermos(){
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_cadastrarUsuario_to_termosDeUso);
    }

}