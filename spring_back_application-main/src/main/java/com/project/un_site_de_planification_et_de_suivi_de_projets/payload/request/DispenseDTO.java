package com.project.un_site_de_planification_et_de_suivi_de_projets.payload.request;

import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.PaymentMethod;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Setter
@Getter
@ToString
@NoArgsConstructor
public class DispenseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float amount;
    private LocalDate date;
    private String description;
    private PaymentMethod paymentMethod;
    private long providerId;
    private String category;

    public DispenseDTO(float amount, LocalDate date, String description, PaymentMethod paymentMethod, long userId) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.paymentMethod = paymentMethod;
        this.providerId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public DispenseDTO(Long id, float amount, LocalDate date, String description, PaymentMethod paymentMethod, long userId, String category) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.paymentMethod = paymentMethod;
        this.providerId = userId;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
