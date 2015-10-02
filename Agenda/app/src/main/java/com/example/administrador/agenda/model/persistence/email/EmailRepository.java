package com.example.administrador.agenda.model.persistence.email;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Email;
import com.example.administrador.agenda.model.persistence.DatabaseHelper;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class EmailRepository {

    private EmailRepository() {
        super();
    }

    public static void save(Email email) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = EmailContract.getContentValues(email);
        if (email.getId() == null) {
            db.insert(EmailContract.TABLE, null, values);
        } else {
            String where = EmailContract.ID + " = ? ";
            String[] params = {email.getId().toString()};
            db.update(EmailContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = EmailContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(EmailContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Email> getAll(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = EmailContract.AGENDA + " = ? ";
        String[] paramns = {String.valueOf(id)};
        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUMNS, where, paramns, null, null, EmailContract.ID);

        List<Email> values = EmailContract.getListEmail(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }

    public static List<Email> getNull() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = EmailContract.AGENDA + " = ? ";
        String[] paramns = {"null"};
        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUMNS, where, paramns, null, null, EmailContract.ID);

        List<Email> values = EmailContract.getListEmail(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }


    public static void updateNull(Agenda agenda){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EmailContract.AGENDA, agenda.get_id());

        String where = EmailContract.AGENDA + " = ? ";
        String[] params = {"null"};
        db.update(EmailContract.TABLE, values, where, params);
    }

    public static Long getId(String email) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = EmailContract.EMAIL + " = ? ";
        String[] paramns = {email};
        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUMNS, where, paramns, null, null, EmailContract.ID);

        Email email1 = EmailContract.getEmail(cursor);

        db.close();
        databaseHelper.close();

        return email1.getId();
    }

    public static void deleteByIdAgenda(Long idAgenda){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = EmailContract.AGENDA + " = ? ";
        String[] params = {String.valueOf(idAgenda)};
        db.delete(EmailContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static void deleteNull(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = EmailContract.AGENDA + " = ? ";
        String[] params = {"null"};
        db.delete(EmailContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }



}
