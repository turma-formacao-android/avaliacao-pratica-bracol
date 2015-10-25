package com.example.administrador.agenda.controller.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrador.agenda.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1284518 on 23/10/2015.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<String> listNomes = new ArrayList<>();

    public RecycleAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        listNomes.add("Wanilton");
        listNomes.add("Ricardo");
        listNomes.add("José");
        listNomes.add("Renatinho");
    }

    public void delete(int position){
        listNomes.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_contact_recycle, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String current = listNomes.get(position);
        holder.imageView.setImageResource(R.mipmap.contact);
        holder.textView.setText(current);
    }

    @Override
    public int getItemCount() {
        return listNomes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.listIcon);
            textView = (TextView) itemView.findViewById(R.id.textView_contact_contact);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            delete(getAdapterPosition());
        }
    }
}
