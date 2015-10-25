package com.example.administrador.agenda.model.persistence.telefone;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Rede;
import com.example.administrador.agenda.model.entidade.Telefone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class TelefoneContract {
    public static final String TABLE = "telefone";
    public static final String ID = "id";
    public static final String AGENDA = "agenda_id";
    public static final String TELEFONE = "telefone";

    public static final String[] COLUMNS = {ID, AGENDA, TELEFONE};

    private TelefoneContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append("( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(AGENDA + " INTEGER, ");
        create.append(TELEFONE + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Telefone telefone) {
        ContentValues values = new ContentValues();
        values.put(TelefoneContract.ID, telefone.getId());
        values.put(TelefoneContract.TELEFONE, telefone.getName());
        if(telefone.getAgenda() == null){
            values.put(TelefoneContract.AGENDA, "null");
        }else {
            values.put(TelefoneContract.AGENDA, telefone.getAgenda().get_id());
        }


        return values;
    }

    public static Telefone getTelefone(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Telefone telefone = new Telefone();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            telefone.setId(cursor.getLong(cursor.getColumnIndex(TelefoneContract.ID)));
            telefone.setName(cursor.getString(cursor.getColumnIndex(TelefoneContract.TELEFONE)));

            Agenda agenda = new Agenda();
            agenda.set_id(cursor.getLong(cursor.getColumnIndex(TelefoneContract.AGENDA)));
            telefone.setAgenda(agenda);

            return telefone;
        }
        return null;
    }


    public static List<Telefone> getListTelefone(Cursor cursor) {
        ArrayList<Telefone> listTelefone = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            listTelefone.add(getTelefone(cursor));

        }
        return listTelefone;
    }
}
