package com.project.un_site_de_planification_et_de_suivi_de_projets.services;

import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Dispense;
import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.User;
import com.project.un_site_de_planification_et_de_suivi_de_projets.exception.UserNotFoundException;
import com.project.un_site_de_planification_et_de_suivi_de_projets.payload.request.DispensesStatisticsDTO;
import com.project.un_site_de_planification_et_de_suivi_de_projets.repos.DispenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DispenseService {

    private final DispenseRepository dispenseRepository;
    private final UserService userService;
    @Autowired
    public DispenseService(DispenseRepository dispenseRepository, UserService userService) {
        this.dispenseRepository = dispenseRepository;
        this.userService = userService;
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

    public List<DispensesStatisticsDTO> calculateDispensesStatistics(long userId) {
        User user = userService.findUserById(userId);
        List<Dispense> dispenses = findDispensesByUserId(userId);
        // Calculate total budget amount
        double totalBudgetAmount = user.getBudget().getAmount();

        List<DispensesStatisticsDTO> statistics = new ArrayList<>();

        // Calculate percentage of expenses per category
        Map<String, Double> categoryAmounts = new HashMap<>();
        for (Dispense dispense : dispenses) {
            String category = dispense.getCategory().getName();
            categoryAmounts.put(category, categoryAmounts.getOrDefault(category, 0.0) +
                    dispense.getAmount());
        }

        for (Map.Entry<String, Double> entry : categoryAmounts.entrySet()) {
            String category = entry.getKey();
            double amount = entry.getValue();
            double percentage = (amount / totalBudgetAmount) * 100.0;
            statistics.add(new DispensesStatisticsDTO(category, percentage));
        }

        return statistics;
    }

    private List<Dispense> findDispensesByUserId(long userId) {
        User user = userService.findUserById(userId);
        return user.getDispenses();
    }
}

