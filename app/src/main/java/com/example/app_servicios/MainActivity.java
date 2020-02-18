package com.example.app_servicios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_servicios.model.Servicio;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//LLAMANDO LATITUD LONGITUD DE OTRA ACTIVIDAD
    Bundle datos;
    Double datolatitud;
    Double datolongitud;

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
        setContentView(R.layout.drawerlayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(
                R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.drawer_open, R.string. drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(
                R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//
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

                    arrayAdapterServicio = new ArrayAdapter<Servicio>(MainActivity.this, android.R.layout.simple_list_item_1, listServicio);
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
        //firebaseDatabase.setPersistenceEnabled(true);// agrega de manera local sin internet
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
       // return true; /** true -> el menú ya está visible */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String servicio_nombre = serv_nomb.getText().toString();
        String servicio_descripcion = serv_descrip.getText().toString();
        String servicio_precio = serv_prec.getText().toString();
        String servicio_categoria = serv_cat.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_add:{
                //Llamando los datoss
                datos=getIntent().getExtras();
                if (datos != null){
                    datolatitud = datos.getDouble("latitud");
                    datolongitud = datos.getDouble("longitud");
                    Log.e("verdatos","latitud:"+datolatitud.toString()+"longitud"+datolongitud.toString());
                 //   prueba.setText("latitud:"+datolatitud.toString()+"longitud"+datolongitud.toString());

                }else {
                    Log.e("verdatos1","NO HAY DATOS");
                }
                //fin de llamado

                if (servicio_nombre.equals("")||servicio_descripcion.equals("")||servicio_precio.equals("")||servicio_categoria.equals("")){
                    validacion();

                }
                else {
                    Servicio p = new Servicio();
                    p.setServicio_id(UUID.randomUUID().toString());
                    p.setServicio_nombre(servicio_nombre);
                    p.setServicio_descripcion(servicio_descripcion);
                    p.setServicio_precio(servicio_precio);
                    p.setServicio_categoria(servicio_categoria);
                    databaseReference.child("Servicio").child(p.getServicio_id()).setValue(p);
                    Toast.makeText(this, "Registro Agregado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }

            case R.id.icon_save:{
                if (servicio_nombre.equals("")||servicio_descripcion.equals("")||servicio_precio.equals("")||servicio_categoria.equals("")){
                    validacion();

                }
                else {
                    Servicio p = new Servicio();
                    p.setServicio_id(UUID.randomUUID().toString());
                    p.setServicio_nombre(servicio_nombre);
                    p.setServicio_descripcion(servicio_descripcion);
                    p.setServicio_precio(servicio_precio);
                    p.setServicio_categoria(servicio_categoria);
                    databaseReference.child("Servicio").child(p.getServicio_id()).setValue(p);
                    Toast.makeText(this, "Registro Actualizado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }

            case R.id.icon_delete:{

                Servicio p = new Servicio();
                p.setServicio_id(servicioSelected.getServicio_id());
                databaseReference.child("Servicio").child(p.getServicio_id()).removeValue();
                Toast.makeText(this,"Registro Eliminado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }

            case R.id.icon_close:{
                finish();
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
        serv_nomb.setText("");
        serv_descrip.setText("");
        serv_prec.setText("");
        serv_cat.setText("");
    }

    private void validacion() {
        String servicio_nombre = serv_nomb.getText().toString();
        String servicio_descripcion = serv_descrip.getText().toString();
        String servicio_precio = serv_prec.getText().toString();
        String servicio_categoria = serv_cat.getText().toString();
        if (servicio_nombre.equals("")){
            serv_nomb.setError("Este campo es requerido");
        }
        else if (servicio_descripcion.equals("")){
            serv_descrip.setError("Este campo es requerido");
        }
        else if (servicio_precio.equals("")){
            serv_prec.setError("Este campo es requerido");
        }
        else if (servicio_categoria.equals("")){
            serv_prec.setError("Este campo es requerido");
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_compartir:
                Intent paramView;
                paramView = new Intent("android.intent.action.SEND");
                paramView.setType("text/plain");
                paramView.putExtra("android.intent.extra.TEXT", "Descarga nuestra app de la PlayStore" +
                        " \n" + "https://play.google.com/store/apps/details?id=app.product.demarktec.alo14_pasajero");
                startActivity(Intent.createChooser(paramView, "Comparte Nuestro aplicativo EASY SERVICE"));
                break;
            case R.id.nav_salir:
                finish();
                break;
            case R.id.MiCuenta:
                startActivity(new Intent(this,MiPerfilActivity.class));
                break;

            case R.id.AcercaDe:
                startActivity(new Intent(this,AcercaDe.class));
                break;

            case R.id.MisServicios:
                startActivity(new Intent(this,MisServicios.class));
                break;

            case R.id.MisPendientes:
                startActivity(new Intent(this,PendientesActivity.class));
                break;

                default:
                    break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(
                R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void IrAlMapa(View view) {
        startActivity(new Intent(this,SeleccionUbicacion.class));
    }

    public void guardar(View view) {
        startActivity(new Intent(this,SeleccionUbicacion.class));
    }
}

