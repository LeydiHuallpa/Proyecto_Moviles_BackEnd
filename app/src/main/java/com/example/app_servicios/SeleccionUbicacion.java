package com.example.app_servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class SeleccionUbicacion extends AppCompatActivity {
final  private int request_code_ask_permission=111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_ubicacion);
        SolicitarPermiso();
    }

    private void SolicitarPermiso() {
        int permisoLocation = ActivityCompat.checkSelfPermission(SeleccionUbicacion.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permisoLocation != PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},request_code_ask_permission);
            }
        }
    }
}
