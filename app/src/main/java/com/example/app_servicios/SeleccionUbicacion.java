package com.example.app_servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

public class SeleccionUbicacion extends AppCompatActivity implements OnMapReadyCallback {
    final private int request_code_ask_permission=111;
    LatLng pos;
    GoogleMap mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_ubicacion);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        pos = new LatLng(-18.011737, -70.253529);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,17));
        int permisoLocation = ActivityCompat.checkSelfPermission(SeleccionUbicacion.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permisoLocation != PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},request_code_ask_permission);
            }
        }
        else {
            mapa.setMyLocationEnabled(true);
            mapa.getUiSettings().setZoomControlsEnabled(false);
            mapa.getUiSettings().setCompassEnabled(true);

        }

    }
    Double Latitud, Longitud;
    public void pedir(View view) {
        if (mapa.getMyLocation() != null) {
            Latitud = mapa.getMyLocation().getLatitude();
            Longitud = mapa.getMyLocation().getLongitude();
            Log.e("lati1",Latitud.toString());
            Log.e("lati2",Longitud.toString());

        } else{
            Toast.makeText(this, "no se ha encontrado su ubicación", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(SeleccionUbicacion.this,Crear_Servicio.class);
        intent.putExtra("latitud",Latitud);
        intent.putExtra("longitud",Longitud);
        startActivity(intent);
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
