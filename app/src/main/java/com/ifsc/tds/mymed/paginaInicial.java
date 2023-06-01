package com.ifsc.tds.mymed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ifsc.tds.mymed.livro.Livro;
import com.ifsc.tds.mymed.remedio.Remedio;
import com.ifsc.tds.mymed.remedio.RemedioViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link paginaInicial#newInstance} factory method to
 * create an instance of this fragment.
 */
public class paginaInicial extends Fragment {
    private FirebaseAuth mAuth; //acessa os recursos de autenticação do Firebase
    private FirebaseAuth.AuthStateListener mAuthListener; //monitora as mudanças de login

    private RemedioViewModel remedioViewModel;
    List<Remedio> lista;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Log.d("MYMED2023", "Usuário logado");
            } else {
                Log.d("MYMED2023", "Usuário signed_out");
                Navigation.findNavController(getView()).navigate(R.id.action_paginaInicial_to_login);
            }
        };


        remedioViewModel = new ViewModelProvider(this).get(RemedioViewModel.class);
        lista = new ArrayList<Remedio>();
        remedioViewModel.getListaRemedios().observe(this, remedios -> {
            lista = remedios;
            Log.d("MYMED2023", "Lista atualizada: " + lista);
            int cont = 0;
            for(Remedio r: lista){
                Log.d("MYMED2023", cont++ + "- Remedio: " + r.getNome());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pagina_inicial, container, false);
        //Botão adicionar Remédio, alterar remédio e barra de tarefas
        Button btnAdd = view.findViewById(R.id.btnAdd);
        ImageButton btnAnotacoes = view.findViewById(R.id.btnAnotacoes);
        ImageButton btnAdd2 = view.findViewById(R.id.cadastrarUsuarioButton);
        ImageButton btnConfig = view.findViewById(R.id.btnConfiguracoes);
        ImageButton btnTermo = view.findViewById(R.id.btnContrato);
        //Listener nos botoes
        btnAdd.setOnClickListener(view1 -> adicionarMed());
        btnAnotacoes.setOnClickListener(view1 -> verAnotacoes());
        btnAdd2.setOnClickListener(view1 -> adicionarMed2());
        btnConfig.setOnClickListener(view1 -> irConfiguracoes());
        btnTermo.setOnClickListener(view1 -> verTermos());
        return view;
    }

    void adicionarMed() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_paginaInicial2_to_addRemedio);
    }

    void adicionarMed2() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_paginaInicial2_to_addRemedio);
    }

    void verAnotacoes() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_paginaInicial2_to_relato);
    }

    void irConfiguracoes() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
//        NavController nav = Navigation.findNavController(getView());
//        nav.navigate(R.id.action_paginaInicial2_to_configuracoes);
    }

    void verTermos() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_paginaInicial2_to_termosDeUso);
    }

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
