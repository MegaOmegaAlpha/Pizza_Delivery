package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Customer extends User {

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(length = 13)
    private String telephone;

    @Column(length = 50)
    private String email;

    @Column(name = "current_number_of_orders")
    private int currentNumberOfOrders;

    public Customer() {
    }

    public Customer(Date birthDate, String telephone, String email, int currentNumberOfOrders, String fullName) {
        this.birthDate = birthDate;
        this.telephone = telephone;
        this.email = email;
        this.currentNumberOfOrders = currentNumberOfOrders;
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCurrentNumberOfOrders() {
        return currentNumberOfOrders;
    }

    public void setCurrentNumberOfOrders(int currentNumberOfOrders) {
        this.currentNumberOfOrders = currentNumberOfOrders;
    }

}
