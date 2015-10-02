package com.example.administrador.agenda.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrador.agenda.model.entidade.Email;
import com.example.administrador.agenda.model.persistence.agenda.AgendaContract;
import com.example.administrador.agenda.model.persistence.email.EmailContract;
import com.example.administrador.agenda.model.persistence.rede.RedeContract;
import com.example.administrador.agenda.model.persistence.telefone.TelefoneContract;
import com.example.administrador.agenda.util.ApplicationUtil;

/**
 * Created by Administrador on 01/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "taskmanagerdb";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(){
        return new DatabaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AgendaContract.getCreateTableScript());
        db.execSQL(TelefoneContract.getCreateTableScript());
        db.execSQL(RedeContract.getCreateTableScript());
        db.execSQL(EmailContract.getCreateTableScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}