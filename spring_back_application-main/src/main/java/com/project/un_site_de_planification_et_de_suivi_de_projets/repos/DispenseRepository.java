package com.project.un_site_de_planification_et_de_suivi_de_projets.repos;

import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Dispense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispenseRepository extends JpaRepository<Dispense,Long> {
    List<Dispense> findByUserId(long userId);
}
