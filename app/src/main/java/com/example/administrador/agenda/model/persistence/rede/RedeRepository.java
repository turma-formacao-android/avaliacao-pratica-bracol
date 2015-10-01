package com.example.administrador.agenda.model.persistence.rede;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public static List<Rede> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(RedeContract.TABLE, RedeContract.COLUMNS, null, null, null, null, RedeContract.ID);

        List<Rede> values = RedeContract.getListRede(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }
    /*
    public static Long getIdByWebId(Long webId){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = TaskContract.WEB_ID + " = ?";
        String[] params = {String.valueOf(webId)};
        String[] colums = {TaskContract.ID};
        Cursor cursor = db.query(TaskContract.TABLE, TaskContract.COLUMNS, where, params, null, null, null);

        if (cursor.getCount() > 0){
            Task task = TaskContract.getTask(cursor);
            return task.getId();
        }
        return (long) 0;

    }
    */
}
