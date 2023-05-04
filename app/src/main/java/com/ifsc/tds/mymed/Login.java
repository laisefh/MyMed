package com.ifsc.tds.mymed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Login extends Fragment {

    public Login() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        Button cadButton = view.findViewById(R.id.botaoCadastrar);
        Button loginButton = view.findViewById(R.id.botaoLogar);
        loginButton.setOnClickListener(view1 -> fazerLogin());
        cadButton.setOnClickListener(view1 -> fazerCadastro());
        return view;
    }
    void fazerLogin(){
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_cadastro_to_paginaInicial2);
    }

    void fazerCadastro(){
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_login_to_cadastro);
    }

}