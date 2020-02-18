package com.example.app_servicios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MiPerfilActivity extends AppCompatActivity {

    private Button mButtonSignOut;
    private TextView miEditTextNombre;
    private TextView miEditTextEmail;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        mButtonSignOut= (Button) findViewById(R.id.btnSignout);

        miEditTextNombre = (TextView) findViewById(R.id.textViewNombre);
        miEditTextEmail = (TextView) findViewById(R.id.textViewEmail);

        mButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MiPerfilActivity.this, LoginActivity.class));
                finish();
            }
        });
        getUserInfo();
    }
    private void getUserInfo(){
        String id= mAuth.getCurrentUser().getUid();
        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                String nombre=dataSnapshot.child("nombre").getValue().toString();
                String email=dataSnapshot.child("email").getValue().toString();

                miEditTextNombre.setText(nombre);
                miEditTextEmail.setText(email);
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
