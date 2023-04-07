package com.project.un_site_de_planification_et_de_suivi_de_projets.controllers;

import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Availability;
import com.project.un_site_de_planification_et_de_suivi_de_projets.exception.ResourceNotFoundException;
import com.project.un_site_de_planification_et_de_suivi_de_projets.repos.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {
    @Autowired
    private AvailabilityRepository availabilityRepository;



    @GetMapping
    public List<Availability> getAvailabilities() {
        return availabilityRepository.findAll();
    }

    @PostMapping
    public Availability createAvailability(@RequestBody Availability availability) {
        return availabilityRepository.save(availability);
    }

    @PutMapping("/{id}")
    public Availability updateAvailability(@PathVariable Long id, @RequestBody Availability availability) {
        Availability existingAvailability = availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability", "id", id));
        existingAvailability.setDate(availability.getDate());
        existingAvailability.setAvailable(availability.isAvailable());
        return availabilityRepository.save(existingAvailability);
    }


}
