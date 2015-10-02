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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrador.agenda.R;
import com.example.administrador.agenda.controller.adapters.EmailAdapter;
import com.example.administrador.agenda.controller.adapters.RedeAdapter;
import com.example.administrador.agenda.controller.adapters.TelefoneAdapter;
import com.example.administrador.agenda.model.Async.AsyncCep;
import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Email;
import com.example.administrador.agenda.model.entidade.Rede;
import com.example.administrador.agenda.model.entidade.Telefone;
import com.example.administrador.agenda.model.persistence.agenda.AgendaRepository;
import com.example.administrador.agenda.model.persistence.email.EmailRepository;
import com.example.administrador.agenda.model.persistence.rede.RedeRepository;
import com.example.administrador.agenda.model.persistence.telefone.TelefoneRepository;
import com.example.administrador.agenda.model.services.AgendaBusinessService;
import com.example.administrador.agenda.model.services.EmailBusinessService;
import com.example.administrador.agenda.model.services.RedeBusinessService;
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
                if (agenda != null) {
                    editTextRua.setText(agenda.getRua());
                    editTextBairro.setText(agenda.getBairro());
                    editTextCidade.setText(agenda.getCidade());
                } else{
                    Toast.makeText(AgendaFormActivity.this, "CEP Não encontrado!" , Toast.LENGTH_LONG).show();
                }


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
        updateEmailList();
        updateRedeList();
        super.onResume();
    }


    private void updateRedeList() {
        List<Rede> listaRede = new ArrayList<>();
        listaRede = agenda.get_id() == null ? RedeBusinessService.findByNull() : RedeBusinessService.findAll(agenda.get_id());

        RedeAdapter adapter = (RedeAdapter) spinnerRede.getAdapter();
        adapter.setItens(listaRede);
        adapter.notifyDataSetChanged();
    }

    private void updateEmailList() {
        List<Email> listaEmail = new ArrayList<>();
        listaEmail = agenda.get_id() == null ? EmailBusinessService.findByNull() : EmailBusinessService.findAll(agenda.get_id());

        EmailAdapter adapter = (EmailAdapter) spinnerEmail.getAdapter();
        adapter.setItens(listaEmail);
        adapter.notifyDataSetChanged();

    }

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
            case R.id.menu_att:
                onMenuAttClick();
                break;
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
        registerForContextMenu(spinnerTel);
    }


    private void bindSpinnerEmail() {
        List<Email> listEmail = new ArrayList<>();
        spinnerEmail = (Spinner) findViewById(R.id.spinnerEmail);
        spinnerEmail.setAdapter(new EmailAdapter(this, listEmail));
        registerForContextMenu(spinnerEmail);

    }

    private void bindSpinnerRede() {
        List<Rede> listRede = new ArrayList<>();
        spinnerRede = (Spinner) findViewById(R.id.spinnerRede);
        spinnerRede.setAdapter(new RedeAdapter(this, listRede));
        registerForContextMenu(spinnerRede);
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
                input.setHint("Número");
                alert.setView(input);
                alert.setTitle("Gerenciar Telefone");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString().trim();
                        saveTel(value);
                    }
                })
                        .setNeutralButton("Nao", null)
                        .create()
                        .show();
            }
        });
    }

    private void saveTel(String value) {
        Telefone tel = new Telefone();
        tel.setName(value);
        tel.setAgenda(agenda);
        TelefoneBusinessService.save(tel);
        updateTelefoneList();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.spinnerTelefone:
                getMenuInflater().inflate(R.menu.menu_telefone_context_list, menu);
                break;
            case R.id.spinnerEmail:
                getMenuInflater().inflate(R.menu.menu_email_context_list, menu);
                break;
            case R.id.spinnerRede:
                getMenuInflater().inflate(R.menu.menu_rede_context_list, menu);
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_telefone_excluir:
                onMenuTelefoneDeleteClick();
                break;
            case R.id.menu_telefone_editar:
                onMenuTelefoneEditClick();
                break;
            case R.id.menu_email_editar:
                onMenuEmailEditClick();
                break;
            case R.id.menu_email_excluir:
                onMenuEmailDeleteClick();
                break;
            case R.id.menu_rede_editar:
                onMenuRedeEditClick();
                break;
            case R.id.menu_rede_excluir:
                onMenuRedeExcluirClick();
                break;

        }
        return super.onContextItemSelected(item);
    }

    private void onMenuRedeExcluirClick() {
        RedeBusinessService.delete((Rede) spinnerRede.getSelectedItem());
        updateRedeList();
    }

    private void onMenuRedeEditClick() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(AgendaFormActivity.this);
        final EditText input = new EditText(AgendaFormActivity.this);
        input.setHint("Rede Social");
        alert.setView(input);
        alert.setTitle("Gerenciar Redes Sociais");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString().trim();

                Rede rede = (Rede) spinnerRede.getSelectedItem();
                editRede(value, rede);
            }
        })
                .setNeutralButton("Não", null)
                .create()
                .show();

    }

    private void editRede(String value, Rede rede) {
        rede.setId(RedeRepository.getId(rede.getRede()));
        rede.setRede(value);
        RedeBusinessService.save(rede);
        updateRedeList();
    }

    private void onMenuEmailDeleteClick() {
        EmailBusinessService.delete((Email) spinnerEmail.getSelectedItem());
        updateEmailList();
    }

    private void onMenuEmailEditClick() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(AgendaFormActivity.this);
        final EditText input = new EditText(AgendaFormActivity.this);
        input.setHint("Email");
        alert.setView(input);
        alert.setTitle("Gerenciar Email");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString().trim();
                editEmail(value);
            }
        })
                .setNeutralButton("Não", null)
                .create()
                .show();
    }

    private void editEmail(String value) {
        Email email = (Email) spinnerEmail.getSelectedItem();
        email.setId(EmailRepository.getId(email.getEmail()));
        email.setEmail(value);
        EmailBusinessService.save(email);
        updateEmailList();
    }

    private void onMenuTelefoneDeleteClick() {
        TelefoneBusinessService.delete((Telefone) spinnerTel.getSelectedItem());
        updateTelefoneList();
    }

    private void onMenuTelefoneEditClick() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(AgendaFormActivity.this);
        final EditText input = new EditText(AgendaFormActivity.this);
        input.setHint("Número");
        alert.setView(input);
        alert.setTitle("Gerenciar Telefone");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString().trim();
                editTelefone(value);
            }
        })
                .setNeutralButton("NÃ£o", null)
                .create()
                .show();
    }

    private void editTelefone(String value) {
        Telefone tel = (Telefone) spinnerTel.getSelectedItem();
        tel.setId(TelefoneRepository.getId(tel.getName()));
        tel.setName(value);
        TelefoneBusinessService.save(tel);
        updateTelefoneList();
    }

    private void bindBtnEmail() {
        btnEmail = (Button) findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent goToTelefoneForm = new Intent(AgendaFormActivity.this, TelefoneActivity.class);
                //startActivity(goToTelefoneForm);
                final AlertDialog.Builder alert = new AlertDialog.Builder(AgendaFormActivity.this);
                final EditText input = new EditText(AgendaFormActivity.this);
                input.setHint("Email");
                alert.setView(input);
                alert.setTitle("Gerenciar Email");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString().trim();
                        saveEmail(value);
                    }
                })
                        .setNeutralButton("Nao", null)
                        .create()
                        .show();
            }
        });
    }

    private void saveEmail(String value) {
        Email email = new Email();
        email.setEmail(value);
        email.setAgenda(agenda);
        EmailBusinessService.save(email);
        updateEmailList();
    }

    private void bindBtnRede() {
        btnRede = (Button) findViewById(R.id.btnRede);
        btnRede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent goToTelefoneForm = new Intent(AgendaFormActivity.this, TelefoneActivity.class);
                //startActivity(goToTelefoneForm);
                final AlertDialog.Builder alert = new AlertDialog.Builder(AgendaFormActivity.this);
                final EditText input = new EditText(AgendaFormActivity.this);
                input.setHint("Rede Social");
                alert.setView(input);
                alert.setTitle("Gerenciar Rede Social");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString().trim();
                        saveRede(value);
                    }
                })
                        .setNeutralButton("Nao", null)
                        .create()
                        .show();
            }
        });

    }

    private void saveRede(String value) {
        Rede rede = new Rede();
        rede.setRede(value);
        rede.setAgenda(agenda);
        RedeBusinessService.save(rede);
        updateRedeList();
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

    @Override
    protected void onStop() {
        TelefoneRepository.deleteNull();
        RedeRepository.deleteNull();
        EmailRepository.deleteNull();
        super.onStop();
    }

    private void onMenuAttClick() {
        Intent goToContactList = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(goToContactList, 127);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (127) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c =  getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String ID = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String phoneNumber;
                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ ID,null, null);
                        phones.moveToNext();
                        phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        salvarContatoExterno(name, phoneNumber);
                        phones.close();
                    }
                }
                break;
        }
    }

    private void salvarContatoExterno(String nome, String numero){
        Agenda a = new Agenda();
        a.setName(nome);
        AgendaRepository.save(a);

        Telefone t = new Telefone();
        t.setName(numero);
        t.setAgenda(a);

        TelefoneBusinessService.save(t);

    }


}
