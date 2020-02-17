package com.example.app_servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Crear_Servicio extends AppCompatActivity {
    Bundle datos;
    TextView prueba;

    Double datolatitud;
    Double datolongitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__servicio);
        prueba=findViewById(R.id.tvprueba);
    }



    public void Insertar(View view) {
        datos=getIntent().getExtras();
        if (datos != null){
             datolatitud = datos.getDouble("latitud");
             datolongitud = datos.getDouble("longitud");
            Log.e("verdatos",datolatitud.toString()+datolongitud.toString());
            prueba.setText("latitud:"+datolatitud.toString()+"longitud"+datolongitud.toString());

        }else {
            Log.e("verdatos1","NO HAY DATOS");
        }

    }

    public void IrAlMapa(View view) {
        startActivity(new Intent(this,SeleccionUbicacion.class));
    }
}
