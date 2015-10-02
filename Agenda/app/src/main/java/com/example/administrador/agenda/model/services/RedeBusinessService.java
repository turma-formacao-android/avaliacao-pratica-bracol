package com.example.administrador.agenda.model.services;

import com.example.administrador.agenda.model.entidade.Rede;
import com.example.administrador.agenda.model.entidade.Rede;
import com.example.administrador.agenda.model.persistence.agenda.AgendaRepository;
import com.example.administrador.agenda.model.persistence.rede.RedeRepository;
import com.example.administrador.agenda.model.persistence.rede.RedeRepository;

import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class RedeBusinessService {
    private RedeBusinessService() {
        super();
    }

    public static List<Rede> findAll(Long id) {
        List<Rede> all = RedeRepository.getAll(id);
        for (Rede rede : all) {
            rede.setAgenda(AgendaRepository.getById(rede.getAgenda().get_id()));
        }
        return all;
    }

    public static List<Rede> findByNull(){
        return RedeRepository.getNull();
    }

    public static void save(Rede rede) {
        RedeRepository.save(rede);
    }


    public static void delete(Rede selectRede) {
        RedeRepository.delete(selectRede.getId());
    }
}
