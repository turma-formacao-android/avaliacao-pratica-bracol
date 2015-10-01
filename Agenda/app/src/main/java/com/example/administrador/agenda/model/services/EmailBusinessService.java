package com.example.administrador.agenda.model.services;

import com.example.administrador.agenda.model.entidade.Agenda;
import com.example.administrador.agenda.model.entidade.Email;
import com.example.administrador.agenda.model.persistence.agenda.AgendaRepository;
import com.example.administrador.agenda.model.persistence.email.EmailRepository;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class EmailBusinessService {
    private EmailBusinessService() {
        super();
    }

    public static List<Email> findAll() {
        List<Email> all = EmailRepository.getAll();
        for (Email email : all) {
            email.setAgenda(AgendaRepository.getById(email.getAgenda().get_id()));
        }
        return all;
    }

    public static void save(Email email) {
        EmailRepository.save(email);
    }


    public static void delete(Email selectEmail) {
        AgendaRepository.delete(selectEmail.getId());
    }
}
