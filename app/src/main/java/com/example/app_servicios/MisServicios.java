package com.example.app_servicios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.app_servicios.model.Servicio;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MisServicios extends AppCompatActivity {
    private List<Servicio> listServicio = new ArrayList<Servicio>();
    ArrayAdapter<Servicio> arrayAdapterServicio;

    EditText serv_nomb, serv_descrip,serv_prec,serv_cat;
    ListView listV_servicios;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Servicio servicioSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_servicios);

        serv_nomb = findViewById(R.id.nombre);
        serv_descrip = findViewById(R.id.descripcion);
        serv_prec = findViewById(R.id.precio);
        serv_cat = findViewById(R.id.categoria);
        //serv_cat = (Spinner) findViewById(R.id.categoria);
        listV_servicios = findViewById(R.id.listV_servicios);

        inicializarFirebase();
        listarDatos();

        listV_servicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                servicioSelected = (Servicio) parent.getItemAtPosition(position);
                serv_nomb.setText(servicioSelected.getServicio_nombre());
                serv_descrip.setText(servicioSelected.getServicio_descripcion());
                serv_prec.setText(servicioSelected.getServicio_precio());
                serv_cat.setText(servicioSelected.getServicio_categoria());

            }

        });
    }

    private void listarDatos() {
        databaseReference.child("Servicio").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listServicio.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Servicio p = objSnaptshot.getValue(Servicio.class);
                    listServicio.add(p);

                    arrayAdapterServicio = new ArrayAdapter<Servicio>(MisServicios.this, android.R.layout.simple_list_item_1, listServicio);
                    listV_servicios.setAdapter(arrayAdapterServicio);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
}
