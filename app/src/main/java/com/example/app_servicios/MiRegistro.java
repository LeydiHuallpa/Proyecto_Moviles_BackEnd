package com.example.app_servicios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MiRegistro extends AppCompatActivity {

    private EditText miEditTextNombre;
    private EditText miEditTextEmail;
    private EditText miEditTextPassword;
    private Button miButtonRegister;
    private Button miButtonSendToLogin;

    //variables de los datos a registrar
    private String nombre ="";
    private String email ="";
    private String password ="";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_registro);

        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        miEditTextNombre = (EditText) findViewById(R.id.et_Usuario);
        miEditTextEmail = (EditText) findViewById(R.id.et_correo);
        miEditTextPassword = (EditText) findViewById(R.id.et_password);
        miButtonRegister = (Button) findViewById(R.id.btn_ir_crearCuenta);
        miButtonSendToLogin = (Button) findViewById(R.id.btnSendToLogin);

        miButtonRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                nombre=miEditTextNombre.getText().toString();
                email=miEditTextEmail.getText().toString();
                password=miEditTextPassword.getText().toString();

                if (!nombre.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    if (password.length()>=6 ){
                        RegisterUser();
                    }
                    else{
                        Toast.makeText(MiRegistro.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MiRegistro.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        miButtonSendToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MiRegistro.this, LoginActivity.class));
                //finish();
            }
        });

    }

    private void RegisterUser()
    {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()) {

                    Map<String, Object> map=new HashMap<>();
                    map.put("nombre", nombre);
                    map.put("email", email);
                    map.put("password", password);

                    String id=mAuth.getCurrentUser().getUid();

                    mDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(MiRegistro.this, LoginActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(MiRegistro.this, "No se pudo crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(MiRegistro.this, "No se pudo regitrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    public void iniciar(View view) {
        startActivity(new Intent(this,LoginActivity.class));

    }
}

