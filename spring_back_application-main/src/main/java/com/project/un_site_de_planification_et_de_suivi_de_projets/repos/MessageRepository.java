package com.project.un_site_de_planification_et_de_suivi_de_projets.repos;

import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
