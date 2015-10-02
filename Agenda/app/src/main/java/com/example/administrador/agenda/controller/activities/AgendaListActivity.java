package com.example.administrador.agenda.controller.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.agenda.R;
import com.example.administrador.agenda.controller.adapters.AgendaAdapter;
import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.persistence.agenda.AgendaRepository;
import com.example.administrador.agenda.model.services.AgendaBusinessService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wanilton on 01/10/2015.
 */
public class AgendaListActivity extends AppCompatActivity {

    public static final String PARAM_AGENDA = "AGENDA";
    private ListView listViewAgenda;
    private Agenda selectAgenda;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_list);

        bindAgendaList();
    }

    private void bindAgendaList() {
        List<Agenda> values = new ArrayList<>();
        listViewAgenda = (ListView) findViewById(R.id.listViewAgendaList);
        registerForContextMenu(listViewAgenda);
        listViewAgenda.setAdapter(new AgendaAdapter(this, values));
        listViewAgenda.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AgendaAdapter adapter = (AgendaAdapter) listViewAgenda.getAdapter();
                selectAgenda = adapter.getItem(position);
                return false;

            }
        });
    }

    @Override
    protected void onResume() {
        updateAgendaList();
        super.onResume();
    }

    private void updateAgendaList() {
        List<Agenda> values = AgendaBusinessService.findAll();
        AgendaAdapter adapter = (AgendaAdapter) listViewAgenda.getAdapter();
        adapter.setItens(values);
        adapter.notifyDataSetChanged();
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agenda_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                onMenuAddClick();
                break;
            case R.id.menu_filter:
                onMenuFilterClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuFilterClick() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        alert.setTitle("Buscar Contato");
        input.setHint("Nome");
        alert.setView(input);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString().trim();
                List<Agenda> listaNomeFilter = AgendaRepository.getAgendaByNome(value);
                AgendaAdapter adapter = (AgendaAdapter) listViewAgenda.getAdapter();
                adapter.setItens(listaNomeFilter);
                adapter.notifyDataSetChanged();

            }
        })
        .setNeutralButton("Não", null)
                .create()
                .show();

    }

    private void onMenuAddClick() {
        Intent goToTaskFormActivity = new Intent(AgendaListActivity.this, AgendaFormActivity.class);
        startActivity(goToTaskFormActivity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_agenda_context_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_excluir:
                onMenuDeleteClick();
                break;
            case R.id.menu_editar:
                onMenuEditClick();
                break;

        }
        return super.onContextItemSelected(item);
    }



    private void onMenuEditClick() {
        Intent goToTaskForm = new Intent(AgendaListActivity.this, AgendaFormActivity.class);
        goToTaskForm.putExtra(AgendaListActivity.PARAM_AGENDA, selectAgenda);
        startActivity(goToTaskForm);

    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(AgendaListActivity.this)
                .setTitle("Confirmação")
                .setMessage("Realmente deseja deletar?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AgendaBusinessService.delete(selectAgenda);
                        selectAgenda = null;
                        String message = "Deletado com sucesso";
                        updateAgendaList();
                        Toast.makeText(AgendaListActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                })
                .setNeutralButton("Não", null)
                .create()
                .show();
    }



}
