package com.example.administrador.agenda.controller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.agenda.R;
import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Telefone;
import com.example.administrador.agenda.model.services.TelefoneBusinessService;

/**
 * Created by Wanilton on 01/10/2015.
 */
public class TelefoneActivity extends AppCompatActivity {

    public static final String PARAM_TELEFONE = "TELEFONE";

    private EditText editTextName;
    private Telefone tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefone_form);

        initTelefone();


        bindEditTextName();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_telefone_salvar:
                onMenuOk();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuOk() {
        bindTelefone();
        TelefoneBusinessService.save(tel);


        finish();
    }

    private void bindTelefone() {
        tel.setName(editTextName.getText().toString());
        tel.setAgenda(null);
    }

    private void initTelefone() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.tel = getIntent().getExtras().getParcelable(PARAM_TELEFONE);
        }
        this.tel = this.tel == null ? new Telefone() : this.tel;
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextTelefoneForm);
        editTextName.setText(tel.getName() == null ? "" : tel.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_telefone_form, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
