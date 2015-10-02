package com.example.administrador.agenda.model.services;

import com.example.administrador.agenda.model.entidade.Telefone;
import com.example.administrador.agenda.model.persistence.agenda.AgendaRepository;
import com.example.administrador.agenda.model.persistence.telefone.TelefoneRepository;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class TelefoneBusinessService {
    private TelefoneBusinessService() {
        super();
    }

    public static List<Telefone> findAll(Long id) {
        List<Telefone> all = TelefoneRepository.getAll(id);
        for (Telefone telefone : all) {
            telefone.setAgenda(AgendaRepository.getById(telefone.getAgenda().get_id()));
        }
        return all;
    }

    public static List<Telefone> findByNull(){
        return TelefoneRepository.getNull();
    }

    public static void save(Telefone telefone) {
        TelefoneRepository.save(telefone);
    }


    public static void delete(Telefone selectTelefone) {
        TelefoneRepository.delete(selectTelefone.getId());
    }

}
