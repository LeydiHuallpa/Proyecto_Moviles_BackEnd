package com.example.app_servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class SeleccionUbicacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_ubicacion);
        SolicitarPermiso();
    }

    private void SolicitarPermiso() {
        int permisoStorage = ActivityCompat.checkSelfPermission(SeleccionUbicacion.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permisoLocation = ActivityCompat.checkSelfPermission(SeleccionUbicacion.this, Manifest.permission.ACCESS_FINE_LOCATION);

       /* if(permisoStorage!= PackageManager.PERMISSION_GRANTED || permisoLocation != PackageManager.PERMISSION_GRANTED){

        }*/
    }
}
