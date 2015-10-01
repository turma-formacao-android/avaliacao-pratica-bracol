package com.example.administrador.agenda.controller.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrador.agenda.R;
import com.example.administrador.agenda.model.entidade.Agenda;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class AgendaAdapter extends BaseAdapter {

    private List<Agenda> listAgenda;
    private Activity context;

    public AgendaAdapter(Activity context, List<Agenda> listAgenda){
        this.context = context;
        this.listAgenda = listAgenda;
    }


    @Override
    public int getCount() {
        return listAgenda.size();
    }

    @Override
    public Agenda getItem(int position) {
        return listAgenda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Agenda agenda = getItem(position);
        View agendaListItem = context.getLayoutInflater().inflate(R.layout.list_item_agenda, parent, false);


        TextView textViewName = (TextView) agendaListItem.findViewById(R.id.textViewName);
        textViewName.setText((agenda.getName().toString()));

        return agendaListItem;
    }

    public void setItens(List<Agenda> itens) {
        listAgenda.clear();
        listAgenda.addAll(itens);
    }

}
