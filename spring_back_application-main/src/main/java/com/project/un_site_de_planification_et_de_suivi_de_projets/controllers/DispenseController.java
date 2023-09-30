package com.project.un_site_de_planification_et_de_suivi_de_projets.controllers;

import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Dispense;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.DispenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispense")
public class DispenseController {
    private final DispenseService dispenseService;

    @Autowired
    public DispenseController(DispenseService dispenseService) {
        this.dispenseService = dispenseService;
    }
    @PostMapping("/add")
    @ResponseBody
    public Dispense addNewDispense(@RequestBody Dispense dispense) {
        return dispenseService.addDispense(dispense);
    }


    @GetMapping("/all")
    @ResponseBody
    public List<Dispense> getAllDispenses(){
        return dispenseService.findAllDispenses();
    }


    @GetMapping("/id/{id}")
    @ResponseBody
    public Dispense getDispenseById(@PathVariable("id") long id){
        return dispenseService.findDispenseById(id);
    }


    @PutMapping("/update")
    @ResponseBody
    public void updateDispense(@RequestBody Dispense dispense){dispenseService.updateDispense(dispense); }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteDispense(@PathVariable("id") long id){dispenseService.deleteDispense(id); }
}
