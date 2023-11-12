package com.project.un_site_de_planification_et_de_suivi_de_projets.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dispenses")
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Dispense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float amount;
    private LocalDate date;
    private String description;
    private PaymentMethod paymentMethod;


    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Dispense() {
    }

    public Dispense(Long id, float amount, LocalDate date, String description, PaymentMethod paymentMethod) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.paymentMethod = paymentMethod;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
