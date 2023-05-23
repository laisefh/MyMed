package com.ifsc.tds.mymed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link paginaInicial#newInstance} factory method to
 * create an instance of this fragment.
 */
public class paginaInicial extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public paginaInicial() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment paginaInicial.
     */
    // TODO: Rename and change types and number of parameters
    public static paginaInicial newInstance(String param1, String param2) {
        paginaInicial fragment = new paginaInicial();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_paginaInicial2_to_configuracoes);
    }

    void verTermos() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_paginaInicial2_to_termosDeUso);
    }
}
