package com.project.un_site_de_planification_et_de_suivi_de_projets.controllers;

import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Availability;
import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Solution;
import com.project.un_site_de_planification_et_de_suivi_de_projets.exception.ResourceNotFoundException;
import com.project.un_site_de_planification_et_de_suivi_de_projets.repos.AvailabilityRepository;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private AvailabilityRepository availabilityRepository;



    @GetMapping
    public List<Solution> getSolutions() {
        return solutionService.findAllSolutions();
    }

    @PostMapping
    public Solution createSolution(@RequestBody Solution solution) {
        return solutionService.addSolution(solution);
    }

    @PostMapping("/{id_solution}/id_solution")
    public Solution createAvailebilitieForSolution(@PathVariable Long id_solution, @RequestBody Availability availability) {
        Solution solution = solutionService.findSolutionById(id_solution);
        Availability availabilityObject = new Availability();
        availabilityObject = availability;
        availabilityObject.setSolution(solution);
        availabilityRepository.save(availabilityObject);
        List<Availability> availabilities = new ArrayList<Availability>();
        availabilities = solution.getAvailabilities();
        availabilities.add(availabilityObject);
        solution.setAvailabilities(availabilities);
        return solutionService.updateSolution(solution);
    }
    
}
