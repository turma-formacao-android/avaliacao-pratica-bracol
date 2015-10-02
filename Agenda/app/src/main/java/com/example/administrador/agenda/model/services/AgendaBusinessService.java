package com.example.administrador.agenda.model.services;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Email;
import com.example.administrador.agenda.model.entidade.Telefone;
import com.example.administrador.agenda.model.persistence.agenda.AgendaRepository;
import com.example.administrador.agenda.model.persistence.email.EmailRepository;
import com.example.administrador.agenda.model.persistence.rede.RedeRepository;
import com.example.administrador.agenda.model.persistence.telefone.TelefoneRepository;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class AgendaBusinessService {

    private AgendaBusinessService() {
        super();
    }

    public static List<Agenda> findAll() {
        List<Agenda> all = AgendaRepository.getAll();
        return all;
    }

    public static void save(Agenda agenda) {
        AgendaRepository.save(agenda);
        TelefoneRepository.updateNull(agenda);
        RedeRepository.updateNull(agenda);
        EmailRepository.updateNull(agenda);
    }


    public static void delete(Agenda selectAgenda) {
        TelefoneRepository.deleteByIdAgenda(selectAgenda.get_id());
        EmailRepository.deleteByIdAgenda(selectAgenda.get_id());
        RedeRepository.deleteByIdAgenda(selectAgenda.get_id());
        AgendaRepository.delete(selectAgenda.get_id());
    }
}
