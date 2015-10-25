package com.example.administrador.agenda.model.http;

import android.location.Address;
import android.os.AsyncTask;
import android.util.Log;

import com.example.administrador.agenda.model.entidade.Agenda;
//import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Wanilton on 01/10/2015.
 */
public class AgendaService {
    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private AgendaService(){
        super();
    }

    public static Agenda getAdressByZipCode(String zipCode){
        Agenda agenda = null;

        try {
            java.net.URL url = new URL(URL + zipCode);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            Log.i("getAdressByZipCode", "Codigo de retorno de requisição de cep: " + responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                //throw new RuntimeException("Error code " + responseCode);
                //InputStream manipulador de arquivo e contem o json
                //tem que adicionar 3 dependencias do jackson tbm
                InputStream inputStream = conn.getInputStream();

                //lendo json
                //ObjectMapper é que da acesso a biblioteca do jackson
                //ObjectMapper objectMapper = new ObjectMapper();
                //agenda = objectMapper.readValue(inputStream, Agenda.class);
            }
            conn.disconnect();

        } catch (Exception e) {
            //faz aparecer mensagem no logcat do android
            Log.e(AgendaService.class.getName(), e.getMessage());
        }


        return agenda;
    }

}
