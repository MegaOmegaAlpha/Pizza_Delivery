package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Administrator extends User {

    @Column(name = "date_when_became_admin")
    private Date becameAdmin;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(length = 13)
    private String telephone;

    @Column(length = 50)
    private String email;

    public Administrator() {
    }

    public Date getBecameAdmin() {
        return becameAdmin;
    }

    public void setBecameAdmin(Date becameAdmin) {
        this.becameAdmin = becameAdmin;
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
}
