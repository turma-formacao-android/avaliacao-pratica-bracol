package com.example.administrador.agenda;

import android.app.Application;

import com.example.administrador.agenda.util.ApplicationUtil;

/**
 * Created by Administrador on 01/10/2015.
 */
public class AgendaManagerApplication extends Application {
    //application manipula ciclo de vida da aplicação

    public void onCreate(){
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }

}
