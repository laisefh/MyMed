package com.ifsc.tds.mymed.data.model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsuarioRepository {

    private DatabaseReference databaseReference;

    public UsuarioRepository() {
        // Inicializar a referência ao Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");
    }

    public void salvarUsuario(String userId, Usuario usuario) {
        databaseReference.child(userId).setValue(usuario);
    }
}
