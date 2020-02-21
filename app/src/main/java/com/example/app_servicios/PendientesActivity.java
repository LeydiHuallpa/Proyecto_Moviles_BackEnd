package com.example.app_servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Vector;

public class PendientesActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MiAdaptador adaptador;
    private Vector<String> misdatos;
    private FloatingActionButton mAddContactFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendientes);

        recyclerView = findViewById(R.id.recycler_view);
        mAddContactFloatingActionButton = findViewById(R.id.volver);
        mAddContactFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PendientesActivity.this, MainActivity.class));
            }
        });
        misdatos = new Vector<String>();
        misdatos.add("Angela Balaguer");
        misdatos.add("Leydi Huallpa");
        int colors[] = {ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, android.R.color.holo_red_light),
                ContextCompat.getColor(this, android.R.color.holo_orange_light),
                ContextCompat.getColor(this, android.R.color.holo_green_light),
                ContextCompat.getColor(this, android.R.color.holo_blue_dark),
                ContextCompat.getColor(this, android.R.color.holo_purple)};
        adaptador = new MiAdaptador(this, misdatos,colors);

        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

}
