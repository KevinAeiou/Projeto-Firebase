package com.example.projetofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EntrarAct extends AppCompatActivity implements View.OnClickListener {

    private TextView txtCadastrar;
    private EditText edtEmail, edtSenha;
    private ProgressBar pb;
    private Button btnEntrar;
    String[] menssagens = {"Preencha todos os campos.", "Login efetuado com sucesso!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_entrar);

        iniciarComponentes();
        txtCadastrar.setOnClickListener(this);
        btnEntrar.setOnClickListener(this);
    }

    private void iniciarComponentes(){
        txtCadastrar = findViewById(R.id.txtCadastrar);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        pb = findViewById(R.id.progresBar);
        btnEntrar = findViewById(R.id.btnEntrar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtCadastrar:
                startActivity(new Intent(this, CadastrarAct.class));
                break;
            case R.id.btnEntrar:
                loginUser(v);
        }
    }

    private void loginUser(View v) {
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        if (email.isEmpty() || senha.isEmpty()){
            Snackbar snackbar = Snackbar.make(v, menssagens[0], Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(Color.WHITE);
            snackbar.setTextColor(Color.BLACK);
            snackbar.show();
        }else {
            autenticarUser(v);
        }
    }

    private void autenticarUser(View v) {
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    pb.setVisibility(View.VISIBLE);

                    new Handler().postDelayed((Runnable) () -> {
                        telaPrincipal();
                    },3000);
                }else {
                    String erro;
                    try {
                        throw task.getException();
                    } catch (Exception e) {
                        erro = "Erro ao logar!";
                    }
                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void telaPrincipal() {
        Intent intent = new Intent(EntrarAct.this, HomeAct.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser userAtual = FirebaseAuth.getInstance().getCurrentUser();

        if (userAtual != null){
            telaPrincipal();
        }
    }
}