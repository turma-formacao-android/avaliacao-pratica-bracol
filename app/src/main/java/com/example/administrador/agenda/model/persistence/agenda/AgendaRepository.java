package com.example.administrador.agenda.model.persistence.agenda;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.persistence.DatabaseHelper;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class AgendaRepository {

    private AgendaRepository() {
        super();
    }

    public static void save(Agenda agenda) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = AgendaContract.getContentValues(agenda);
        if (agenda.get_id() == null) {
            db.insert(AgendaContract.TABLE, null, values);
        } else {
            String where = AgendaContract.ID + " = ? ";
            String[] params = {agenda.get_id().toString()};
            db.update(AgendaContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = AgendaContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(AgendaContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Agenda> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(AgendaContract.TABLE, AgendaContract.COLUMNS, null, null, null, null, AgendaContract.ID);

        List<Agenda> values = AgendaContract.getListAgenda(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }

    public static Agenda getById(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = AgendaContract.ID + " = ?";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(AgendaContract.TABLE, AgendaContract.COLUMNS, where, params, null, null, null, null);
        Agenda label = AgendaContract.getAgenda(cursor);

        db.close();
        databaseHelper.close();

        return label;
    }

    public static Agenda getLastAgenda(Agenda agenda){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = AgendaContract.NAME + " = ? AND " + AgendaContract.RUA + " = ?";
        String[] params = {agenda.getName(), agenda.getRua()};
        Cursor cursor = db.query(AgendaContract.TABLE, AgendaContract.COLUMNS, where, params, null, null, null);


        Agenda agenda1 = AgendaContract.getAgenda(cursor);

        return agenda1;
    }

    public static List<Agenda> getAgendaByNome(String nome){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = AgendaContract.NAME + " LIKE '%" + nome + "%' ";
        String[] params = {nome};
        Cursor cursor = db.query(AgendaContract.TABLE, AgendaContract.COLUMNS, where, null, null, null, null);


        List<Agenda> values = AgendaContract.getListAgenda(cursor);

        return values;
    }


}
