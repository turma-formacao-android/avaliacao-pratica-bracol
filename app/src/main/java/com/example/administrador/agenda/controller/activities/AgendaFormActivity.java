package com.example.administrador.agenda.controller.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.administrador.agenda.R;
import com.example.administrador.agenda.controller.adapters.AgendaAdapter;
import com.example.administrador.agenda.controller.adapters.TelefoneAdapter;
import com.example.administrador.agenda.model.Async.AsyncCep;
import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Rede;
import com.example.administrador.agenda.model.entidade.Telefone;
import com.example.administrador.agenda.model.persistence.agenda.AgendaRepository;
import com.example.administrador.agenda.model.services.AgendaBusinessService;
import com.example.administrador.agenda.model.services.TelefoneBusinessService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class AgendaFormActivity extends AppCompatActivity {

    public static final String PARAM_AGENDA = "AGENDA";
    private EditText editTextName;
    private EditText editTextCEP;
    private EditText editTextRua;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private Button btnTel;
    private Button btnEmail;
    private Button btnRede;
    private Button btnCep;
    private Spinner spinnerTel;
    private Spinner spinnerEmail;
    private Spinner spinnerRede;
    private Agenda agenda;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_agenda_form);

        initAgenda();

        bindEditTextName();
        bindEditTextCep();
        bindEditTextRua();
        bindEditTextBairro();
        bindEditTextCidade();
        bindBtnTel();
        bindBtnEmail();
        bindBtnRede();
        bindBtnCep();
        bindSpinnerTel();
        bindSpinnerEmail();
        bindSpinnerRede();
        bindToolbar();

    }

    private void bindToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void bindBtnCep() {
        btnCep = (Button) findViewById(R.id.btnCep);
        btnCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GerarCamposAsync();
            }
        });
    }

    private void GerarCamposAsync() {
        new AsyncCep() {
            @Override
            protected void onPostExecute(Agenda agenda) {
                super.onPostExecute(agenda);
                editTextRua.setText(agenda.getRua());
                editTextBairro.setText(agenda.getBairro());
                editTextCidade.setText(agenda.getCidade());
            }
        }.execute(editTextCEP.getText().toString());
    }

    private void bindEditTextCidade() {
        editTextCidade = (EditText) findViewById(R.id.editTextFormCidade);
        editTextCidade.setText(agenda.getCidade() == null ? "" : agenda.getCidade());

    }

    private void bindEditTextBairro() {
        editTextBairro = (EditText) findViewById(R.id.editTextFormBairro);
        editTextBairro.setText(agenda.getBairro() == null ? "" : agenda.getBairro());
    }

    private void bindEditTextRua() {
        editTextRua = (EditText) findViewById(R.id.editTextFormRua);
        editTextRua.setText(agenda.getRua() == null ? "" : agenda.getRua());
    }

    @Override
    protected void onResume() {
        updateTelefoneList();
        //updateEmailList();
        //updateRedeList();
        super.onResume();
    }

    /*
    private void updateRedeList() {
        List<Rede> labels = LabelBusinessServices.findAll();
        LabelListAdapter adapter = (LabelListAdapter) spinnerLabel.getAdapter();
        adapter.setItens(labels);
        adapter.notifyDataSetChanged();
    }

    private void updateEmailList() {
        List<Label> labels = LabelBusinessServices.findAll();
        LabelListAdapter adapter = (LabelListAdapter) spinnerLabel.getAdapter();
        adapter.setItens(labels);
        adapter.notifyDataSetChanged();

    }*/

    private void updateTelefoneList() {
        List<Telefone> listaTelefone = new ArrayList<>();

        listaTelefone = agenda.get_id() == null ? TelefoneBusinessService.findByNull() : TelefoneBusinessService.findAll(agenda.get_id());

        TelefoneAdapter adapter = (TelefoneAdapter) spinnerTel.getAdapter();
        adapter.setItens(listaTelefone);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agenda_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_agenda_salvar:
                onMenuOk();
                break;
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuOk() {
        bindAgenda();
        AgendaBusinessService.save(agenda);

        finish();
    }

    private void bindSpinnerTel() {
        List<Telefone> listTelefone = new ArrayList<>();
        spinnerTel = (Spinner) findViewById(R.id.spinnerTelefone);
        spinnerTel.setAdapter(new TelefoneAdapter(this, listTelefone));
        spinnerTel.setOnLongClickListener(new AdapterView.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(AgendaFormActivity.this);
                final EditText input = new EditText(AgendaFormActivity.this);
                alert.setView(input);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString().trim();
                        Telefone tel = (Telefone) spinnerTel.getSelectedItem();
                        tel.setName(value);
                        TelefoneBusinessService.save(tel);
                        updateTelefoneList();
                    }
                })
                        .setNeutralButton("Não", null)
                        .create()
                        .show();
                return false;
            }
        });
    }


    private void bindSpinnerEmail() {
        spinnerEmail = (Spinner) findViewById(R.id.spinnerEmail);

    }

    private void bindSpinnerRede() {
        spinnerEmail = (Spinner) findViewById(R.id.spinnerRede);

    }

    private void bindBtnTel() {
        btnTel = (Button) findViewById(R.id.btnTelefone);
        btnTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent goToTelefoneForm = new Intent(AgendaFormActivity.this, TelefoneActivity.class);
                //startActivity(goToTelefoneForm);
                final AlertDialog.Builder alert = new AlertDialog.Builder(AgendaFormActivity.this);
                final EditText input = new EditText(AgendaFormActivity.this);
                alert.setView(input);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString().trim();
                        Telefone tel = new Telefone();
                        tel.setName(value);
                        TelefoneBusinessService.save(tel);
                        adicionarItemAdapterTelefone(tel);
                    }
                })
                        .setNeutralButton("Não", null)
                        .create()
                        .show();
            }
        });
    }

    private void adicionarItemAdapterTelefone(Telefone tel) {
        TelefoneAdapter adapter = (TelefoneAdapter) spinnerTel.getAdapter();
        adapter.addItem(tel);
        adapter.notifyDataSetChanged();

    }

    private void bindBtnEmail() {
        btnEmail = (Button) findViewById(R.id.btnEmail);
    }

    private void bindBtnRede() {
        btnRede = (Button) findViewById(R.id.btnRede);

    }


    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextFormName);
        editTextName.setText(agenda.getName() == null ? "" : agenda.getName());
    }

    private void bindEditTextCep() {
        editTextCEP = (EditText) findViewById(R.id.editTextFormCep);
        editTextCEP.setText(agenda.getCep() == null ? "" : agenda.getCep());
    }

    private void initAgenda() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.agenda = getIntent().getExtras().getParcelable(PARAM_AGENDA);
        }
        this.agenda = this.agenda == null ? new Agenda() : this.agenda;
    }

    private void bindAgenda() {
        agenda.setName(editTextName.getText().toString());
        agenda.setCep(editTextCEP.getText().toString());
        agenda.setRua(editTextRua.getText().toString());
        agenda.setBairro(editTextBairro.getText().toString());
        agenda.setCidade(editTextCidade.getText().toString());

    }


}
