package com.example.administrador.agenda.model.persistence.rede;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Email;
import com.example.administrador.agenda.model.entidade.Rede;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class RedeContract {
    public static final String TABLE = "rede";
    public static final String ID = "id";
    public static final String AGENDA = "agenda_id";
    public static final String REDE = "rede";

    public static final String[] COLUMNS = {ID, AGENDA, REDE};

    private RedeContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append("( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(AGENDA + " INTEGER, ");
        create.append(REDE + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Rede rede) {
        ContentValues values = new ContentValues();
        values.put(RedeContract.ID, rede.getId());
        values.put(RedeContract.REDE, rede.getRede());
        values.put(RedeContract.AGENDA, rede.getAgenda().get_id());


        return values;
    }

    public static Rede getRede(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Rede rede = new Rede();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            rede.setId(cursor.getLong(cursor.getColumnIndex(RedeContract.ID)));
            rede.setRede(cursor.getString(cursor.getColumnIndex(RedeContract.REDE)));

            Agenda agenda = new Agenda();
            agenda.set_id(cursor.getLong(cursor.getColumnIndex(RedeContract.AGENDA)));
            rede.setAgenda(agenda);

            return rede;
        }
        return null;
    }


    public static List<Rede> getListRede(Cursor cursor) {
        ArrayList<Rede> listRede = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            listRede.add(getRede(cursor));

        }
        return listRede;
    }
}
