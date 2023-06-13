package com.ifsc.tds.mymed;
import com.ifsc.tds.mymed.data.model.usuarioViewModel;

import android.os.Bundle;

import androidx.annotation.Nullable;
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
import android.widget.EditText;
import android.widget.ImageButton;

import com.ifsc.tds.mymed.data.model.usuarioViewModel;
import com.ifsc.tds.mymed.usuario.Usuario;
import com.ifsc.tds.mymed.usuario.UsuarioViewModel;

import java.time.LocalDate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link configuracoes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class configuracoes extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText editnome;
    private EditText editEmail;
    private EditText editDia;
    private EditText editMes;
    private EditText editAno;
    UsuarioViewModel usuarioViewModel;

    public configuracoes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment configuracoes.
     */
    // TODO: Rename and change types and number of parameters
    public static configuracoes newInstance(String param1, String param2) {
        configuracoes fragment = new configuracoes();
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
        View view = inflater.inflate(R.layout.fragment_configuracoes, container, false);
        //botoes
        ImageButton btnAnotacoes = view.findViewById(R.id.btnConfigAnotacoes);
        ImageButton btnAdd = view.findViewById(R.id.btnConfigAdicionar);
        ImageButton btnHome = view.findViewById(R.id.btnConfigHome);
        ImageButton btnTermo = view.findViewById(R.id.btnConfigContrato);
        //EditText
        EditText editnome = view.findViewById(R.id.cadastrarNomeEditText);
        EditText editEmail = view.findViewById(R.id.editTextConfigEmail);
        EditText editDia = view.findViewById(R.id.editTextConfigDia);
        EditText editMes = view.findViewById(R.id.editTextConfigMes);
        EditText editAno = view.findViewById(R.id.editTextConfigAno);
        //On listener dos botôes
        btnAnotacoes.setOnClickListener(view1 -> verAnotacoes());
        btnHome.setOnClickListener(view1 -> irParaHome());
        btnAdd.setOnClickListener(view1 -> addMed());
        btnTermo.setOnClickListener(view1 -> verTermos());

        Button salvarButton = view.findViewById(R.id.btnConfigSalvar);
        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarConfiguracoes();
            }
        });

        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);
        usuarioViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                if (usuario != null) {
                    String nome = usuario.getNome();
                    editnome.setText(nome);
                    String email = usuario.getEmail();
                    editEmail.setText(email);
                    String dia = usuario.getDataNascimento();
                    editDia.setText(dia);
                    String mes = usuario.getDataNascimento();
                    editMes.setText(mes);
                    String ano = usuario.getDataNascimento();
                    editAno.setText(ano);
                }
            }
        });
        return view;
    }
    void salvarConfiguracoes(){
        String nome = editnome.getText().toString();
        String email = editEmail.getText().toString();
        int dia = Integer.parseInt(editDia.getText().toString());
        int mes = Integer.parseInt(editMes.getText().toString());
        int ano = Integer.parseInt(editAno.getText().toString());
        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);
        Log.d("MYMED2023", "Data Nascimento:" + dataNascimento);
        usuarioViewModel.updateRelato(nome);
        usuarioViewModel.updateRelato(email);
        usuarioViewModel.updateRelato(dataNascimento.toString());
//        relatoViewModel.insertRelato(uuid, relato);
        //Volta para tela anterior
        NavController nav = Navigation.findNavController(getView());
    }

    void irParaHome() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_configuracoes_to_paginaInicial2);
    }

    void verAnotacoes() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_configuracoes_to_relato);
    }

    void addMed() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_configuracoes_to_addRemedio);
    }

    void verTermos() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_configuracoes_to_termosDeUso);
    }


}