package com.project.un_site_de_planification_et_de_suivi_de_projets.payload.request;

public class DispensesStatisticsDTO {
    private String category;
    private double percentage;

    public DispensesStatisticsDTO(String category, double percentage) {
        this.category = category;
        this.percentage = percentage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
