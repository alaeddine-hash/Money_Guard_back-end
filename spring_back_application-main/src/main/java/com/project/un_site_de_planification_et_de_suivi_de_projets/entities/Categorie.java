package com.project.un_site_de_planification_et_de_suivi_de_projets.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "categories")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name ;

    @OneToMany(mappedBy="categorie")
    private Set<SousCategorie> sousCategories;
}
