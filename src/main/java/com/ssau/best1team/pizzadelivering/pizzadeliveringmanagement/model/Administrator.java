package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Administrator extends User {

    @Column(name = "date_when_became_admin")
    private Date becameAdmin;

}
