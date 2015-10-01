package com.example.administrador.agenda.model.persistence.telefone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Telefone;
import com.example.administrador.agenda.model.persistence.DatabaseHelper;
import com.example.administrador.agenda.model.persistence.agenda.AgendaContract;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class TelefoneRepository {

    private TelefoneRepository() {
        super();
    }

    public static void save(Telefone telefone) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = TelefoneContract.getContentValues(telefone);
        if (telefone.getId() == null) {
            db.insert(TelefoneContract.TABLE, null, values);
        } else {
            String where = TelefoneContract.ID + " = ? ";
            String[] params = {telefone.getId().toString()};
            db.update(TelefoneContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = TelefoneContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(TelefoneContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Telefone> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(TelefoneContract.TABLE, TelefoneContract.COLUMNS, null, null, null, null, TelefoneContract.ID);

        List<Telefone> values = TelefoneContract.getListTelefone(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }


    public static String getByAgendaId(Long agendaID){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = TelefoneContract.AGENDA + " = ?";
        String[] params = {String.valueOf(agendaID)};
        String[] colums = {TelefoneContract.TELEFONE};
        Cursor cursor = db.query(TelefoneContract.TABLE, colums, where, params, null, null, null);


        Telefone telefone = TelefoneContract.getTelefone(cursor);
        return telefone.getName();
    }

}
