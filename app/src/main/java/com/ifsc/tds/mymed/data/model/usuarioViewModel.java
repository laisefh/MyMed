package com.ifsc.tds.mymed.data.model;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class usuarioViewModel extends ViewModel {
    private FirebaseAuth firebaseAuth;
    private UsuarioRepository usuarioRepository;

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
