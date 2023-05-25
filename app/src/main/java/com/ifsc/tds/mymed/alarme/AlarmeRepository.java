package com.ifsc.tds.mymed.alarme;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifsc.tds.mymed.remedio.Remedio;

public class AlarmeRepository {
    private DatabaseReference databaseReference;

    public AlarmeRepository() {
        // Inicializar a referência ao Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("alarmes");
    }

    public void salvarAlarme(String alarmeId, Alarme alarme ) {
        databaseReference.child(alarmeId).setValue(alarme);
    }
}
