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
        Button loginButton = view.findViewById(R.id.botaoCadastrar);
        loginButton.setOnClickListener(view1 -> fazerCadastro());
        return view;
    }

    void fazerCadastro(){
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_login_to_cadastro);
    }

}