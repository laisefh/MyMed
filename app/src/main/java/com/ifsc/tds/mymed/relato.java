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
 * Use the {@link relato#newInstance} factory method to
 * create an instance of this fragment.
 */
public class relato extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public relato() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment relato.
     */
    // TODO: Rename and change types and number of parameters
    public static relato newInstance(String param1, String param2) {
        relato fragment = new relato();
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
        View view = inflater.inflate(R.layout.fragment_relato, container, false);

        //botoes
        Button btnSalvar = view.findViewById(R.id.btnRelatoSalvar);
        ImageButton btnAdd = view.findViewById(R.id.btnRelatoAdicionar);
        ImageButton btnHome = view.findViewById(R.id.btnRelatoHome);
        ImageButton btnConfig = view.findViewById(R.id.btnRelatoConfiguracoes);
        ImageButton btnTermo = view.findViewById(R.id.btnRelatoContrato);
        //On listener dos botôes
        btnSalvar.setOnClickListener(view1 -> salvar());
        btnAdd.setOnClickListener(view1 -> addMed());
        btnHome.setOnClickListener(view1 -> irParaHome());
        btnConfig.setOnClickListener(view1 -> irConfiguracoes());
        btnTermo.setOnClickListener(view1 -> verTermos());

        return view;
    }
    void salvar() {
    }
    void irParaHome() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_relato_to_paginaInicial2);
    }
    void addMed() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_relato_to_addRemedio);
    }
    void irConfiguracoes() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_relato_to_configuracoes);
    }
    void verTermos() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_relato_to_termosDeUso);
    }


}