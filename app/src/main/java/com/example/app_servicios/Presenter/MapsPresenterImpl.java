package com.example.app_servicios.Presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.app_servicios.SeleccionUbicacion;
import com.example.app_servicios.View.MapsView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MapsPresenterImpl implements MapsPresenter{
    final private int request_code_ask_permission=111;
    LatLng pos;
    MapsView mapsView;
    Context ctx;
    GoogleMap mapa;
    public MapsPresenterImpl(MapsView mapsView) {
        this.mapsView=mapsView;
        ctx=mapsView.getContext();
    }

    @Override
    public void verificarpermisos() {
        int permisoLocation = ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permisoLocation != PackageManager.PERMISSION_GRANTED){
            mapsView.PermisoOn();
        }else {
            mapsView.PErmisoOff();
        }
    }

    @Override
    public void GuardarPosicion() {
        if (mapa.getMyLocation() != null) {
            mapsView.ubicacionOn();

        } else{
            mapsView.ubicacionOf();
        }

    }
}
