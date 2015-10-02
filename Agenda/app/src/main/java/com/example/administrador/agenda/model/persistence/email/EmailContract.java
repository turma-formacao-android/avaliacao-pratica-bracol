package com.example.administrador.agenda.model.persistence.email;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class EmailContract {

    public static final String TABLE = "email";
    public static final String ID = "id";
    public static final String AGENDA = "agenda_id";
    public static final String EMAIL = "email";

    public static final String[] COLUMNS = {ID, AGENDA, EMAIL};

    private EmailContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append("( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(AGENDA + " INTEGER, ");
        create.append(EMAIL + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Email email) {
        ContentValues values = new ContentValues();
        values.put(EmailContract.ID, email.getId());
        values.put(EmailContract.EMAIL, email.getEmail());
        if(email.getAgenda().get_id() == null){
            values.put(EmailContract.AGENDA, "null");
        }else {
            values.put(EmailContract.AGENDA, email.getAgenda().get_id());
        }


        return values;
    }

    public static Email getEmail(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Email email = new Email();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            email.setId(cursor.getLong(cursor.getColumnIndex(EmailContract.ID)));
            email.setEmail(cursor.getString(cursor.getColumnIndex(EmailContract.EMAIL)));

            Agenda agenda = new Agenda();
            agenda.set_id(cursor.getLong(cursor.getColumnIndex(EmailContract.AGENDA)));
            email.setAgenda(agenda);

            return email;
        }
        return null;
    }


    public static List<Email> getListEmail(Cursor cursor) {
        ArrayList<Email> listEmail = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            listEmail.add(getEmail(cursor));

        }
        return listEmail;
    }

}
