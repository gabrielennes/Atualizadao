package com.example.myapplication;

import androidx.annotation.NonNull;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends Activity {

    private FirebaseAuth mAuth;

    private EditText editEmail;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();

        editEmail = findViewById(R.id.campoEmail);
        editSenha = findViewById(R.id.campoSenha);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user){
        if(user != null){
            Intent i = new Intent(login.this, Main.class);
            startActivity(i);
        }
    }

    public void login(View view){
        String email = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        if(email.equals("")) {
            editEmail.setError("Preencha este campo!");
            return;
        }
        if(senha.equals("")) {
            editSenha.setError("Preencha este campo!");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    updateUI(mAuth.getCurrentUser());
                } else {
                    Toast.makeText(login.this, "Usuário ou senha incorreta!", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    public void cadastro(View view) {
        Intent i = new Intent(login.this, Cadastro.class);
        startActivity(i);
    }
}