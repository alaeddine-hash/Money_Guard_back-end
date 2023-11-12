package com.project.un_site_de_planification_et_de_suivi_de_projets.payload.request;

public class DescriptionDTO {
    private String description;

    public DescriptionDTO() {
    }

    public DescriptionDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
