package com.example.administrador.agenda.model.services;

import com.example.administrador.agenda.model.entidade.Email;
import com.example.administrador.agenda.model.entidade.Rede;
import com.example.administrador.agenda.model.persistence.agenda.AgendaRepository;
import com.example.administrador.agenda.model.persistence.email.EmailRepository;
import com.example.administrador.agenda.model.persistence.rede.RedeRepository;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class RedeBusinessService {
    private RedeBusinessService() {
        super();
    }

    public static List<Rede> findAll() {
        List<Rede> all = RedeRepository.getAll();
        for (Rede rede : all) {
            rede.setAgenda(AgendaRepository.getById(rede.getAgenda().get_id()));
        }
        return all;
    }

    public static void save(Rede rede) {
        RedeRepository.save(rede);
    }


    public static void delete(Rede selectRede) {
        AgendaRepository.delete(selectRede.getId());
    }
}
