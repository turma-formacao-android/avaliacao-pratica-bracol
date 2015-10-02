package com.example.administrador.agenda.controller.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.agenda.R;
import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Telefone;

import java.util.List;

/**
 * Created by Wanilton on 01/10/2015.
 */
public class TelefoneAdapter extends BaseAdapter {

    private List<Telefone> listTelefone;
    private Activity context;

    public TelefoneAdapter(Activity context, List<Telefone> listTelefone){
        this.context = context;
        this.listTelefone = listTelefone;
    }


    @Override
    public int getCount() {
        return listTelefone.size();
    }

    @Override
    public Telefone getItem(int position) {
        return listTelefone.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Telefone telefone = getItem(position);
        View telefoneaListItem = context.getLayoutInflater().inflate(R.layout.list_item_telefone, parent, false);


        TextView textViewName = (TextView) telefoneaListItem.findViewById(R.id.textViewTelefone);
        textViewName.setText((telefone.getName().toString()));

        return telefoneaListItem;
    }

    public void setItens(List<Telefone> itens) {
        listTelefone.clear();
        listTelefone.addAll(itens);
    }

    public void addItem(Telefone item){
        listTelefone.add(item);
    }

}