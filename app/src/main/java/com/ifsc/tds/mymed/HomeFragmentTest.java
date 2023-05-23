package com.ifsc.tds.mymed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifsc.tds.mymed.data.model.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HomeFragmentTest extends Fragment {
    private FirebaseAuth mAuth; //acessa os recursos de autenticação do Firebase
    private DatabaseReference mDatabase; //acessa os recursos do banco de dados do Firebase
    private ValueEventListener mValueListener; //monitora as mudanças nos dados do usuário

    private TextView nameTextView;
    private TextView emailTextView;
    private TextView birthdayTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Acesso os dados do usuário identificado para ler seu UUI
        mAuth = FirebaseAuth.getInstance();
        String uui = mAuth.getCurrentUser().getUid();
        //Acessa os dados do usuário conectado
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios").child(uui);
        mValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Os dados retornados são convertidos em uma classe Usuario
                Usuario usuario = snapshot.getValue(Usuario.class);
                Log.d("MyMed", "Dados do usuário carregados");
                nameTextView.setText(usuario.getNome());
                emailTextView.setText(usuario.getEmail());
                //Código necessário para fazer a conversão das datas no padrão pretendido
                String date = usuario.getDataNascimento();
                LocalDate myDateObj = LocalDate.parse(date);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = myDateObj.format(myFormatObj);
                birthdayTextView.setText(formattedDate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_test, container, false);
        //Configura as referências aos componentes da UI
        nameTextView = view.findViewById(R.id.nomeUsuarioTextViewHome);
        emailTextView = view.findViewById(R.id.emailUsuarioTextViewHome);
        birthdayTextView = view.findViewById(R.id.dataNascimentoTextViewHome);
        //Adiciona uma ação ao botão logout
        Button logout = view.findViewById(R.id.logoutHomeButton);
        logout.setOnClickListener(view1 -> logout());
        return view;
    }

    public void logout(){
        mAuth.signOut();
        NavController nav = Navigation.findNavController(getView());
        nav.popBackStack();
    }

    /*** NECESSÁRIO PARA DESATIVAR O MONITORAMENTO COM O APP MINIMIZADO/MAXIMIZADO ***/
    @Override
    public void onStart() {
        super.onStart();
        mDatabase.addValueEventListener(mValueListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mValueListener != null) {
            mDatabase.removeEventListener(mValueListener);
        }
    }
}