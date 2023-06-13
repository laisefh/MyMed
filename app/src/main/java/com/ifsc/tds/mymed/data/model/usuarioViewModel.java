package com.ifsc.tds.mymed.data.model;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class usuarioViewModel extends ViewModel {
    private FirebaseAuth firebaseAuth;
    private UsuarioRepository usuarioRepository;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    public usuarioViewModel() {
        // Inicializar o Firebase Auth e o repositório do usuário
        firebaseAuth = FirebaseAuth.getInstance();
        usuarioRepository = new UsuarioRepository();
    }

    public void registrarUsuario(String nome, String email, String senha, String dataNascimento, OnRegistroCompleteListener listener) {
        firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // O usuário foi registrado com sucesso
                        String userId = firebaseAuth.getCurrentUser().getUid();
                        Usuario usuario = new Usuario(nome, email, senha, dataNascimento);
                        usuarioRepository.salvarUsuario(userId, usuario);

                        listener.onRegistroSuccess();
                    } else {
                        // Ocorreu um erro no registro do usuário
                        listener.onRegistroError(task.getException().getMessage());
                    }
                });
    }


    public interface OnRegistroCompleteListener {
        void onRegistroSuccess();
        void onRegistroError(String mensagemErro);
    }

}
