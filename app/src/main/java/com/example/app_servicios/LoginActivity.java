package com.example.app_servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.app_servicios.Login.LoginInterface;
import com.example.app_servicios.Login.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginInterface.View
{

    private EditText miEditTextEmail;
    private EditText miEditTextPassword;
    private Button miButtonLogin;
    private MaterialDialog dialog;
    //declarar al rpesentador
    private LoginInterface.Presenter presenter;

    private String email ="";
    private String password ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViews();

        miButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });
    }

    private void setViews()
    {
          presenter = new LoginPresenter(this);
          miEditTextEmail = (EditText) findViewById(R.id.et_correo);
          miEditTextPassword = (EditText) findViewById(R.id.et_password);
          miButtonLogin = (Button) findViewById(R.id.btn_login);
          MaterialDialog.Builder builder = new MaterialDialog.Builder(this)

                .title("CARGANDO")

                .content("Espere porfavor ...")

                .cancelable(false)

                .progress(true, 0);//true para que sea indeterminado y que no tenga maximo
          dialog = builder.build();
    }

    private void setInputs(boolean enable){
        //cuando llamemos a disable inputs lo desactivara
        miEditTextEmail.setEnabled(enable);
        miEditTextPassword.setEnabled(enable);
        miButtonLogin.setEnabled(enable);

    }

    public void registro(View view) {
        startActivity(new Intent(this,MiRegistro.class));

    }

    public void Inicia(View view)  {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hiderogress() {
        dialog.dismiss();
    }

    @Override
    public void handleLogin() {
           if(!isValidEmail()){
               Toast.makeText(LoginActivity.this, "No es un email valido", Toast.LENGTH_SHORT).show();
           } else if(!isValidPassword()) {
               Toast.makeText(LoginActivity.this, "No es un password valido", Toast.LENGTH_SHORT).show();
           } else {
               //trim: para que no haya espacios
               presenter.toLogin(miEditTextEmail.getText().toString().trim(), miEditTextPassword.getText().toString().trim());
           }
    }

    @Override
    public boolean isValidEmail() {
        // si el email tiene cuerpo de email
        //nos devolvera true, sino false
        return Patterns.EMAIL_ADDRESS.matcher(miEditTextEmail.getText().toString()).matches();
    }

    @Override
    public boolean isValidPassword() {
        //TextUtils.isEmpty: si esta vacio y es menor que 4, no es valido
        if(TextUtils.isEmpty(miEditTextPassword.getText().toString()) && miEditTextPassword.getText().toString().length()<4){
            Toast.makeText(LoginActivity.this, "No es una contraseña valida", Toast.LENGTH_SHORT).show();
            miEditTextPassword.setError("No es una contraseña valida");
            return false; // devuelve false
        } else{
            return true;
        }
    }

    @Override
    public void onLogin() {
        Toast.makeText(LoginActivity.this, "Has hecho Login correctamente", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        ///cuando se cieerre la app, destroza la vista
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();//muestra el error que envia el tasklistener

    }
}

