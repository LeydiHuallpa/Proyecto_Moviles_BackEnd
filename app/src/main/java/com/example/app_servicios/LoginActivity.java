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

public class LoginActivity extends AppCompatActivity {

    private EditText miEditTextEmail;
    private EditText miEditTextPassword;
    private Button miButtonLogin;

    private String email ="";
    private String password ="";

    FirebaseAuth mAuth;
    //DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth= FirebaseAuth.getInstance();
       // mDatabase= FirebaseDatabase.getInstance().getReference();

        miEditTextEmail = (EditText) findViewById(R.id.et_correo);
        miEditTextPassword = (EditText) findViewById(R.id.et_password);
        miButtonLogin = (Button) findViewById(R.id.btn_login);

        miButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            email = miEditTextEmail.getText().toString();
            password = miEditTextPassword.getText().toString();
            if(!email.isEmpty() && !password.isEmpty()){
                loginUser();
            } else {
                Toast.makeText(LoginActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "No se puede iniciar sesion / Compruebe los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void registro(View view) {
        startActivity(new Intent(this,MiRegistro.class));

    }

    public void Inicia(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}

