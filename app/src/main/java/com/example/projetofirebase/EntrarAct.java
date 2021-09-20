package com.example.projetofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EntrarAct extends AppCompatActivity implements View.OnClickListener {

    TextView txtCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_entrar);

        txtCadastrar = findViewById(R.id.txtCadastrar);
        txtCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtCadastrar:
                startActivity(new Intent(this, CadastrarAct.class));
                break;
        }
    }
}