package com.project.un_site_de_planification_et_de_suivi_de_projets.services;

import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Dispense;
import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.User;
import com.project.un_site_de_planification_et_de_suivi_de_projets.exception.UserNotFoundException;
import com.project.un_site_de_planification_et_de_suivi_de_projets.repos.DispenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispenseService {

    private final DispenseRepository dispenseRepository;
    @Autowired
    public DispenseService(DispenseRepository dispenseRepository) {
        this.dispenseRepository = dispenseRepository;
    }

    public Dispense addDispense(Dispense dispense) {
        return dispenseRepository.save(dispense);
    }

    public List<Dispense> findAllDispenses() {
        return dispenseRepository.findAll();
    }

    public Dispense updateDispense(Dispense dispense) {
        return dispenseRepository.save(dispense);
    }

    public Dispense findDispenseById(Long id) {
        return dispenseRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Dispense by id : " + id + " was not found"));
    }

    public void deleteDispense(Long id){
        dispenseRepository.deleteById(id);
    }
}
