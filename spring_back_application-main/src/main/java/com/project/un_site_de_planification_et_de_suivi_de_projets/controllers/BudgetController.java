package com.project.un_site_de_planification_et_de_suivi_de_projets.controllers;

import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Budget;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    @PostMapping("/add")
    @ResponseBody
    public Budget addNewBudget(@RequestBody Budget budget) {
        return budgetService.addBudget(budget);
    }


    @GetMapping("/all")
    @ResponseBody
    public List<Budget> getAllBudgets(){
        return budgetService.findAllBudgets();
    }


    @GetMapping("/id/{id}")
    @ResponseBody
    public Budget getBudgetById(@PathVariable("id") long id){
        return budgetService.findBudgetById(id);
    }


    @PutMapping("/update")
    @ResponseBody
    public void updateBudget(@RequestBody Budget budget){budgetService.updateBudget(budget); }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteBudget(@PathVariable("id") long id){budgetService.deleteBudget(id); }
}
