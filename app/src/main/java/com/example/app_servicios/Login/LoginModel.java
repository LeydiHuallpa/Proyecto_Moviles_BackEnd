package com.example.app_servicios.Login;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.app_servicios.LoginActivity;
import com.example.app_servicios.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel implements LoginInterface.Model {
    private  LoginInterface.TaskListener listener;
    FirebaseAuth mAuth;


    public LoginModel(LoginInterface.TaskListener listener) {
        this.listener = listener;
        mAuth= FirebaseAuth.getInstance();
    }

    @Override
    public void doLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    listener.onSuccess();
                }else{
                    if(task.getException()!=null)
                    listener.onError(task.getException().getMessage());
                }
            }
        });
    }
}
