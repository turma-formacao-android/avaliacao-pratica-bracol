package com.example.administrador.agenda.model.persistence.agenda;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.agenda.model.entidade.Agenda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class AgendaContract {

    public static final String TABLE = "agenda";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CEP = "cep";
    public static final String RUA = "rua";
    public static final String BAIRRO = "bairro";
    public static final String CIDADE = "cidade";

    public static final String[] COLUMNS = {ID, NAME, CEP, RUA, BAIRRO, CIDADE};

    private AgendaContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append("( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT, ");
        create.append(CEP + " TEXT, ");
        create.append(RUA + " TEXT, ");
        create.append(BAIRRO + " TEXT, ");
        create.append(CIDADE + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Agenda agenda) {
        ContentValues values = new ContentValues();
        values.put(AgendaContract.ID, agenda.get_id());
        values.put(AgendaContract.NAME, agenda.getName());
        values.put(AgendaContract.CEP, agenda.getCep());
        values.put(AgendaContract.RUA, agenda.getRua());
        values.put(AgendaContract.BAIRRO, agenda.getBairro());
        values.put(AgendaContract.CIDADE, agenda.getCidade());


        return values;
    }

    public static Agenda getAgenda(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Agenda agenda = new Agenda();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            agenda.set_id(cursor.getLong(cursor.getColumnIndex(AgendaContract.ID)));
            agenda.setName(cursor.getString(cursor.getColumnIndex(AgendaContract.NAME)));
            agenda.setCep(cursor.getString(cursor.getColumnIndex(AgendaContract.CEP)));
            agenda.setRua(cursor.getString(cursor.getColumnIndex(AgendaContract.RUA)));
            agenda.setBairro(cursor.getString(cursor.getColumnIndex(AgendaContract.BAIRRO)));
            agenda.setCidade(cursor.getString(cursor.getColumnIndex(AgendaContract.CIDADE)));

            return agenda;
        }
        return null;
    }


    public static List<Agenda> getListAgenda(Cursor cursor) {
        ArrayList<Agenda> listAgenda = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            listAgenda.add(getAgenda(cursor));

        }
        return listAgenda;
    }

}
