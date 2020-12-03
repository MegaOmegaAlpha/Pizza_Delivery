package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto;

import java.util.Date;

public class CustomerDTO {

    private int id;
    private Date birthDate;
    private String telephone;
    private String email;
    private int currentNumberOfOrders;
    private String fullName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
