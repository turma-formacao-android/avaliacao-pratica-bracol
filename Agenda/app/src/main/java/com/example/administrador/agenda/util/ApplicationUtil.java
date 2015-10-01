package com.example.administrador.agenda.util;

import android.content.Context;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ApplicationUtil {

    private static Context APPLICATION_CONTEXT;

    private ApplicationUtil() {
        super();
    }

    public static void setContext(Context context){
        APPLICATION_CONTEXT = context;
    }

    public static Context getContext(){
        return ApplicationUtil.APPLICATION_CONTEXT;
    }

}
