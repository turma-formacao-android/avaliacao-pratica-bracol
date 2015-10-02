package com.example.administrador.agenda.model.persistence.rede;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Rede;
import com.example.administrador.agenda.model.persistence.DatabaseHelper;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class RedeRepository {

    private RedeRepository() {
        super();
    }

    public static void save(Rede rede) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = RedeContract.getContentValues(rede);
        if (rede.getId() == null) {
            db.insert(RedeContract.TABLE, null, values);
        } else {
            String where = RedeContract.ID + " = ? ";
            String[] params = {rede.getId().toString()};
            db.update(RedeContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = RedeContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(RedeContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Rede> getAll(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = RedeContract.AGENDA + " = ? ";
        String[] paramns = {String.valueOf(id)};
        Cursor cursor = db.query(RedeContract.TABLE, RedeContract.COLUMNS, where, paramns, null, null, RedeContract.ID);

        List<Rede> values = RedeContract.getListRede(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }

    public static List<Rede> getNull() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = RedeContract.AGENDA + " = ? ";
        String[] paramns = {"null"};
        Cursor cursor = db.query(RedeContract.TABLE, RedeContract.COLUMNS, where, paramns, null, null, RedeContract.ID);

        List<Rede> values = RedeContract.getListRede(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }


    public static void updateNull(Agenda agenda){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RedeContract.AGENDA, agenda.get_id());

        String where = RedeContract.AGENDA + " = ? ";
        String[] params = {"null"};
        db.update(RedeContract.TABLE, values, where, params);
    }

    public static Long getId(String rede) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = RedeContract.REDE + " = ? ";
        String[] paramns = {rede};
        Cursor cursor = db.query(RedeContract.TABLE, RedeContract.COLUMNS, where, paramns, null, null, RedeContract.ID);

        Rede rede1 = RedeContract.getRede(cursor);

        db.close();
        databaseHelper.close();

        return rede1.getId();
    }

    public static void deleteByIdAgenda(Long idAgenda){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = RedeContract.AGENDA + " = ? ";
        String[] params = {String.valueOf(idAgenda)};
        db.delete(RedeContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static void deleteNull(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = RedeContract.AGENDA + " = ? ";
        String[] params = {"null"};
        db.delete(RedeContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }
}
