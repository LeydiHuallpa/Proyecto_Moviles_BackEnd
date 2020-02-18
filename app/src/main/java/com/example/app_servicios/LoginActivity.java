package com.example.app_servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    public void registro(View view) {
        startActivity(new Intent(this,MiRegistro.class));

    }

    public void Inicia(View view) {
        startActivity(new Intent(this,MainActivity.class));

    }
}

