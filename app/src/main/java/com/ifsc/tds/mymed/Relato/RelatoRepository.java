package com.ifsc.tds.mymed.Relato;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifsc.tds.mymed.data.model.Usuario;
import com.ifsc.tds.mymed.remedio.Remedio;

public class RelatoRepository {
    private DatabaseReference databaseReference;

    public RelatoRepository() {
        // Inicializar a referência ao Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("relatos");
    }

    public void salvarRelato(String relatoId, Relato relato ) {
        databaseReference.child(relatoId).setValue(relato);
    }
}
