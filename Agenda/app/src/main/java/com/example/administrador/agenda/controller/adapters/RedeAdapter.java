package com.example.administrador.agenda.controller.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.agenda.R;
import com.example.administrador.agenda.model.entidade.Rede;
import com.example.administrador.agenda.model.entidade.Telefone;

import java.util.List;

/**
 * Created by Administrador on 02/10/2015.
 */
public class RedeAdapter extends BaseAdapter {

    private List<Rede> listRede;
    private Activity context;

    public RedeAdapter(Activity context, List<Rede> listRede){
        this.context = context;
        this.listRede = listRede;
    }


    @Override
    public int getCount() {
        return listRede.size();
    }

    @Override
    public Rede getItem(int position) {
        return listRede.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Rede rede = getItem(position);
        View redeListItem = context.getLayoutInflater().inflate(R.layout.list_item_rede, parent, false);


        TextView textViewName = (TextView) redeListItem.findViewById(R.id.textViewRede);
        textViewName.setText((rede.getRede().toString()));

        return redeListItem;
    }

    public void setItens(List<Rede> itens) {
        listRede.clear();
        listRede.addAll(itens);
    }

    public void addItem(Rede item){
        listRede.add(item);
    }

}
