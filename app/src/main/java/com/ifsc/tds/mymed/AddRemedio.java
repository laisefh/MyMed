package com.ifsc.tds.mymed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import android.widget.RadioGroup;

import com.ifsc.tds.mymed.remedio.RemedioViewModel;

public class AddRemedio extends Fragment {
    static int DIARIAMENTE = 1;
    static int INTERVALO_HORA = 2;

    RemedioViewModel remedioViewModel;

    EditText nomeEditText;
    EditText horaInicialEditText;
    EditText anotacoesEditText;
    RadioGroup tipoFrequencia;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        remedioViewModel = new ViewModelProvider(this).get(RemedioViewModel.class);
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

        nomeEditText = view.findViewById(R.id.editTextRemedio);
        horaInicialEditText = view.findViewById(R.id.editTextHorario);
        anotacoesEditText = view.findViewById(R.id.editTxtAnotacoes);
        tipoFrequencia = view.findViewById(R.id.radioGroupTipo);

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
        String nome = nomeEditText.getText().toString();
        String hora = horaInicialEditText.getText().toString();
        String anotacoes = anotacoesEditText.getText().toString();

        int selecionado = tipoFrequencia.getCheckedRadioButtonId();
        int tipoFrequencia = 0;
        if (selecionado == R.id.rdbtnDiario)
            tipoFrequencia = DIARIAMENTE;
        else if (selecionado == R.id.rdbtnHoras)
            tipoFrequencia = INTERVALO_HORA;

        remedioViewModel.insertRemedio(nome,hora,anotacoes, tipoFrequencia);
        //Volta para tela anterior
        NavController nav = Navigation.findNavController(getView());
        nav.popBackStack();
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