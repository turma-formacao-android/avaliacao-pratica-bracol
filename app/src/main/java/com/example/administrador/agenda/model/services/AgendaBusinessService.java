package com.example.administrador.agenda.model.services;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Telefone;
import com.example.administrador.agenda.model.persistence.agenda.AgendaRepository;
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
        Agenda agenda2 = AgendaRepository.getLastAgenda(agenda);
        TelefoneRepository.updateNull(agenda2);
    }


    public static void delete(Agenda selectAgenda) {
        AgendaRepository.delete(selectAgenda.get_id());
    }
}
