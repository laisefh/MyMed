package com.ifsc.tds.mymed.remedio;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifsc.tds.mymed.data.model.Usuario;

public class remedioRepository {

    private DatabaseReference databaseReference;

    public remedioRepository() {
        // Inicializar a referência ao Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("remedios");
    }

    public void salvarRemedio(String remedioId, Remedio remedio ) {
        databaseReference.child(remedioId).setValue(remedio);
    }
}
