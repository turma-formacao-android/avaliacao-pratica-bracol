package com.example.administrador.agenda.model.services;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.persistence.agenda.AgendaRepository;

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

    public static void save(Agenda task) {
        AgendaRepository.save(task);
    }


    public static void delete(Agenda selectAgenda) {
        AgendaRepository.delete(selectAgenda.get_id());
    }
}
