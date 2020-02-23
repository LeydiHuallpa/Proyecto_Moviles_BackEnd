package com.example.app_servicios.View;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;

public interface MapsView {
    void PermisoOn();
    void PErmisoOff();
    Context getContext();
    void ubicacionOn();
    void ubicacionOf();


}
