package com.example.administrador.agenda.model.services;

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

    public static List<Email> findAll(Long id) {
        List<Email> all = EmailRepository.getAll(id);
        for (Email email : all) {
            email.setAgenda(AgendaRepository.getById(email.getAgenda().get_id()));
        }
        return all;
    }

    public static List<Email> findByNull(){
        return EmailRepository.getNull();
    }

    public static void save(Email email) {
        EmailRepository.save(email);
    }


    public static void delete(Email selectEmail) {
        EmailRepository.delete(selectEmail.getId());
    }
}
