package com.example.app_servicios.Login;

public interface LoginInterface {

    //va a controlartodo lo que la app hace de manera visual
    interface View {
        void disableInputs();////desactivar los editext

        void enableInputs();/////activa los edt

        void showProgress();///muestra el progreso cuando envia datos al preesentador

        void hiderogress();///oculta

        void handleLogin();//empieza a trabajar para validar los datos, se encarga de quetodo funcione

        boolean isValidEmail();///esto lo podria hacer el presentador

        boolean isValidPassword();///esto igual

        void onLogin();

        void onError(String error);///porque el presentador nos devuelve el error    }
    }
 // se encarga de trabajar con la vista:
// necesita esos recursos de la vista para hacer el login
    interface Presenter{
        //tiene que tener esto para que elimine la vista y descarguemos recursos de telefono, cuaando cambiamos de activida a actividad
//evitamos de que android se ralentize
     void onDestroy();
     void toLogin(String email, String password);///enviar estos datos al modelo

    }
//se encarga de hacer esa transaccion
    interface Model{
    void doLogin(String email, String password);
    }
//si el login ha sido bueno o no, siempre esta escuchando
    interface TaskListener{
    void onSuccess();
    void onError(String error);
    }
}
