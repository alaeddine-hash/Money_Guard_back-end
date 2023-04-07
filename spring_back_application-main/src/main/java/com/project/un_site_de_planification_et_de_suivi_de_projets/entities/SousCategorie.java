package com.project.un_site_de_planification_et_de_suivi_de_projets.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table()
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SousCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="categorie_id", nullable=false)
    private Categorie categorie;

    @OneToMany(mappedBy="sousCategorie")
    private Set<Solution> solutions;

    @OneToMany(mappedBy="sousCategorie")
    private Set<Favorite> favorites;
}
