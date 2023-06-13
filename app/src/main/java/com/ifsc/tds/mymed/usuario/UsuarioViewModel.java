package com.ifsc.tds.mymed.usuario;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;

public class UsuarioViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuario;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public LiveData<Usuario> getUsuario() {
        if (usuario == null) {
            usuario = new MutableLiveData<Usuario>();
            carregarUsuario();
        }
        return usuario;
    }

    private void carregarUsuario(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uuid = auth.getCurrentUser().getUid();
        DatabaseReference usuarioRef = database.getReference("usuarios").child(uuid);
        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario user = snapshot.getValue(Usuario.class);
                user.setId(snapshot.getKey());
                usuario.postValue(user);
                Log.d("MYMED2023", "Usuario lido: " + user.nome);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("MYMED2023", "Failed to read value.", error.toException());
            }
        });
    }


    public void updateRelato(String relato) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uuid = auth.getCurrentUser().getUid();
        DatabaseReference relatoRef = database.getReference("usuarios").child(uuid).child("relato");
        relatoRef.setValue(relato).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Log.d("MYMED2023", "Relato editado ");
            else
                Log.d("MYMED2023", "Não foi possível editar o relato");
        });
    }

    public void updateNome(String nome) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uuid = auth.getCurrentUser().getUid();
        DatabaseReference relatoRef = database.getReference("usuarios").child(uuid).child("nome");
        relatoRef.setValue(nome).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Log.d("MYMED2023", "Relato editado ");
            else
                Log.d("MYMED2023", "Não foi possível editar o relato");
        });
    }

    public void updateEmail(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uuid = auth.getCurrentUser().getUid();
        DatabaseReference relatoRef = database.getReference("usuarios").child(uuid).child("email");
        relatoRef.setValue(email).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Log.d("MYMED2023", "Relato editado ");
            else
                Log.d("MYMED2023", "Não foi possível editar o relato");
        });
    }

    public void updateData(String toString) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uuid = auth.getCurrentUser().getUid();
        DatabaseReference relatoRef = database.getReference("usuarios").child(uuid).child("dataNascimento");
        relatoRef.setValue(toString).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Log.d("MYMED2023", "Relato editado ");
            else
                Log.d("MYMED2023", "Não foi possível editar o relato");
        });
    }
}
