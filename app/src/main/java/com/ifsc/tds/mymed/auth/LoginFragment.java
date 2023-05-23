package com.ifsc.tds.mymed.auth;



import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ifsc.tds.mymed.R;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth; //acessa os recursos de autenticação do Firebase
    private FirebaseAuth.AuthStateListener mAuthListener; //monitora as mudanças de login

    //Variáveis para referenciar os elementos da UI
    private EditText login;
    private EditText password;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Log.d("MyMed", "signed_in");
                goToHome();
            }
            else {
                Log.d("MyMed", "signed_out");
                //fazerAlgumaCoisa
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        login = view.findViewById(R.id.editTextEmail);
        password = view.findViewById(R.id.editTextTextPassword2);
        Button loginButton = view.findViewById(R.id.botaoLogar);
        loginButton.setOnClickListener(view1 -> fazerLogin());
        Button cadButton = view.findViewById(R.id.botaoCadastrar);
        cadButton.setOnClickListener(view1 -> fazerCadastro());
        return view;
    }

    void fazerCadastro(){
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_login_to_cadastro);
    }

    /***MÉTODO PARA USAR O SERVIÇO DE AUTENTICAÇÃO POR EMAIL DO FIREBASE***/
    void fazerLogin(){
        String username = login.getText().toString();
        String password2 = password.getText().toString();
        //Faz uma tentativa de login com os dados preenchidos na UI
        mAuth.signInWithEmailAndPassword(username,password2)
                //Método assíncrono para lidar com a resposta da solicitação
                .addOnCompleteListener(getActivity(),
                        task -> {
                            if (!task.isSuccessful()) {
                                Log.w("MyMed", "Falha ao efetuar o Login: ", task.getException());
                            }else{
                                Log.d("MyMed", "Login Efetuado com sucesso!!!");
                                NavController nav = Navigation.findNavController(getView());
                                nav.navigate(R.id.action_cadastro_to_paginaInicial);
                            }
                        });
    }

    /*** MÉTODO SIMPLES PARA ENCAMINHAR PARA A TELA PRINCIPAL ***/
    void goToHome(){
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_cadastro_to_paginaInicial);
    }

    /*** NECESSÁRIO PARA DESATIVAR O MONITORAMENTO COM O APP MINIMIZADO/MAXIMIZADO ***/
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}