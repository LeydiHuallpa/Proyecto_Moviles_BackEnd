package com.example.app_servicios.Login;

import com.example.app_servicios.Login.LoginInterface;
import com.example.app_servicios.LoginActivity;

public class LoginPresenter implements LoginInterface.Presenter, LoginInterface.TaskListener {

    private LoginInterface.View view;
    private LoginInterface.Model model;

    public LoginPresenter(LoginInterface.View view) {
        this.view = view;
        model=new LoginModel(this);
    }

    @Override
    public void onDestroy() {
        view = null;

    }

    @Override
    public void toLogin(String email, String password) {
        if(view!=null){
            view.disableInputs();
            view.showProgress();
        }
        model.doLogin(email, password);

    }

    @Override
    public void onSuccess() {
        if(view!=null){
            view.enableInputs();
            view.hiderogress();
            view.onLogin();
        }
    }

    @Override
    public void onError(String error) {
        if(view!=null){
            view.hiderogress();
            view.onError(error);
        }
    }
}
