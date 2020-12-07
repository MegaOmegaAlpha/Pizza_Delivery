package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto;

import java.util.Date;

public class CustomerDTO {

    private long id;
    private Date birthDate;
    private String telephone;
    private String email;
    private int currentNumberOfOrders;
    private String fullName;
    private String login;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
