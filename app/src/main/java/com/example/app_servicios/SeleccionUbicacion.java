package com.example.app_servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.app_servicios.Presenter.MapsPresenter;
import com.example.app_servicios.Presenter.MapsPresenterImpl;
import com.example.app_servicios.View.MapsView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

public class SeleccionUbicacion extends AppCompatActivity implements OnMapReadyCallback, MapsView {
    GoogleMap mapa;
    final private int request_code_ask_permission=111;
    LatLng pos;
    MapsView mapsView;
    MapsPresenter mapsPresenter;
    Double Latitud, Longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_ubicacion);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        mapsPresenter = new MapsPresenterImpl(this);
        //iniciando llamado
        mapsPresenter.verificarpermisos();
    }

     @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa= googleMap;
         pos = new LatLng(-18.011737, -70.253529);
         mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,17));
         if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
             mapa.setMyLocationEnabled(true);
             mapa.getUiSettings().setZoomControlsEnabled(false);
             mapa.getUiSettings().setCompassEnabled(true);
         }
    }


    public void guardar(View view) {
        mapsPresenter.GuardarPosicion();
    }


    @Override
    public void PermisoOn() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},request_code_ask_permission);
        }

    }

    @Override
    public void PErmisoOff() {
        Toast.makeText(SeleccionUbicacion.this, "UBICACION ACTIVADA", Toast.LENGTH_SHORT).show();

    }

    @Override
    public Context getContext() {
        return(this);
    }


    @Override
    public void ubicacionOn() {
        Latitud = mapa.getMyLocation().getLatitude();
        Longitud = mapa.getMyLocation().getLongitude();
        Log.e("lati1",Latitud.toString());
        Log.e("lati2",Longitud.toString());
        Intent intent = new Intent(SeleccionUbicacion.this,MainActivity.class);
        intent.putExtra("latitud",Latitud);
        intent.putExtra("longitud",Longitud);
        startActivity(intent);
    }

    @Override
    public void ubicacionOf() {
        Toast.makeText(this, "no se ha encontrado su ubicación", Toast.LENGTH_SHORT).show();

    }
}
