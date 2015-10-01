package com.example.administrador.agenda.controller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.administrador.agenda.R;
import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Rede;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class AgendaFormActivity extends AppCompatActivity{

    public static final String PARAM_AGENDA = "AGENDA";
    private EditText editTextName;
    private EditText editTextCEP;
    private EditText editTextRua;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private Button btnTel;
    private Button btnEmail;
    private Button btnRede;
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
        bindSpinnerTel();
        bindSpinnerEmail();
        bindSpinnerRede();

    }

    @Override
    protected void onResume() {
        updateTelefoneList();
        updateEmailList();
        updateRedeList();
        super.onResume();
    }

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

    }

    private void updateTelefoneList() {
        List<Label> labels = LabelBusinessServices.findAll();
        LabelListAdapter adapter = (LabelListAdapter) spinnerLabel.getAdapter();
        adapter.setItens(labels);
        adapter.notifyDataSetChanged();

    }

    private void bindSpinnerTel() {
        spinnerEmail = (Spinner) findViewById(R.id.spinnerEmail);

    }

    private void bindSpinnerEmail() {
        spinnerEmail = (Spinner) findViewById(R.id.spinnerEmail);

    }

    private void bindSpinnerRede() {
        spinnerEmail = (Spinner) findViewById(R.id.spinnerRede);

    }

    private void bindBtnTel() {
        btnTel = (Button) findViewById(R.id.btnTelefone);

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



}
