package com.project.un_site_de_planification_et_de_suivi_de_projets.services;

import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Solution;
import com.project.un_site_de_planification_et_de_suivi_de_projets.exception.UserNotFoundException;
import com.project.un_site_de_planification_et_de_suivi_de_projets.repos.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionService {

    private static SolutionRepository solutionRepository;

    @Autowired
    public SolutionService(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;

    }

    public Solution addSolution(Solution solution) {
        return solutionRepository.save(solution);
    }

    public List<Solution> findAllSolutions() {
        return solutionRepository.findAll();
    }

    public Solution updateSolution(Solution solution) {
        return solutionRepository.save(solution);
    }

    public Solution findSolutionById(Long id) {
        return solutionRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("solution by id " + id + " was not found"));
    }

    public void deleteSolution(Long id) {
        solutionRepository.deleteById(id);
    }

}
