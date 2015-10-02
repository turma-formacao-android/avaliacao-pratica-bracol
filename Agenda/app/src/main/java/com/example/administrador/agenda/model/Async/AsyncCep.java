package com.example.administrador.agenda.model.Async;

import android.os.AsyncTask;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.http.AgendaService;

/**
 * Created by Wanilton on 01/10/2015.
 */
public class AsyncCep extends AsyncTask<String, Void, Agenda> {

        @Override
        protected Agenda doInBackground(String... params) {
            return AgendaService.getAdressByZipCode(params[0]);
        }

}
