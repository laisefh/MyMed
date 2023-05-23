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

public class addRemedio extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_remedio, container, false);
        //botoes
        Button btnSalvar = view.findViewById(R.id.adicionarRemedioButton);
        Button btnExcluir = view.findViewById(R.id.btnExcluir);

        ImageButton btnAnotacoes = view.findViewById(R.id.btnAddRemedioAnotacoes);
        ImageButton btnHome = view.findViewById(R.id.btnAddRemedioHome);
        ImageButton btnConfig = view.findViewById(R.id.btnAddRemedioConfiguracoes);
        ImageButton btnTermo = view.findViewById(R.id.btnAddRemedioContrato);


        //On listener dos botôes
        btnSalvar.setOnClickListener(view1 -> salvar());
        btnExcluir.setOnClickListener(view1 -> excluir());
        btnAnotacoes.setOnClickListener(view1 -> verAnotacoes());
        btnHome.setOnClickListener(view1 -> irParaHome());
        btnConfig.setOnClickListener(view1 -> irConfiguracoes());
        btnTermo.setOnClickListener(view1 -> verTermos());

        return view;
    }
    void salvar() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_addRemedio_to_paginaInicial2);
    }

    void excluir() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_addRemedio_to_paginaInicial2);
    }
    void irParaHome() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_addRemedio_to_paginaInicial2);
    }

    void verAnotacoes() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_addRemedio_to_relato);
    }

    void irConfiguracoes() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_addRemedio_to_configuracoes);
    }

    void verTermos() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_addRemedio_to_termosDeUso);
    }

}