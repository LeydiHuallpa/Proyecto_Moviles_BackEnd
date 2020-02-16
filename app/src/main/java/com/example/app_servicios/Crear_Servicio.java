package com.example.app_servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Crear_Servicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__servicio);
    }

    public void Insertar(View view) {

    }

    public void IrAlMapa(View view) {
        startActivity(new Intent(this,SeleccionUbicacion.class));

    }
}
