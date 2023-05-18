package com.ifsc.tds.mymed.addRemedio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.ifsc.tds.mymed.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addRemedio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addRemedio extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addRemedio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addRemedio.
     */
    // TODO: Rename and change types and number of parameters
    public static addRemedio newInstance(String param1, String param2) {
        addRemedio fragment = new addRemedio();
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
        View view = inflater.inflate(R.layout.fragment_add_remedio, container, false);
        //botoes
        Button btnSalvar = view.findViewById(R.id.btnSalvar);
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