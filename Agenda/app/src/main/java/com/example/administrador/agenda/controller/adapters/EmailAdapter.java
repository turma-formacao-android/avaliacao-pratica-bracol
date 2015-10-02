package com.example.administrador.agenda.controller.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.agenda.R;
import com.example.administrador.agenda.model.entidade.Email;
import com.example.administrador.agenda.model.entidade.Rede;

import java.util.List;

/**
 * Created by Administrador on 02/10/2015.
 */
public class EmailAdapter extends BaseAdapter {

    private List<Email> listEmail;
    private Activity context;

    public EmailAdapter(Activity context, List<Email> listEmail){
        this.context = context;
        this.listEmail = listEmail;
    }


    @Override
    public int getCount() {
        return listEmail.size();
    }

    @Override
    public Email getItem(int position) {
        return listEmail.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Email email = getItem(position);
        View emailListItem = context.getLayoutInflater().inflate(R.layout.list_item_email, parent, false);


        TextView textViewName = (TextView) emailListItem.findViewById(R.id.textViewEmail);
        textViewName.setText((email.getEmail().toString()));

        return emailListItem;
    }

    public void setItens(List<Email> itens) {
        listEmail.clear();
        listEmail.addAll(itens);
    }

    public void addItem(Email item){
        listEmail.add(item);
    }

}
