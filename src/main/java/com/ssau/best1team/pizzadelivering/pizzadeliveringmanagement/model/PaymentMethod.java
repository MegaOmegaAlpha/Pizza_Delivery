package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "paymentMethod")
    private List<ChosenPaymentMethod> chosenPaymentMethodList;

    public PaymentMethod() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChosenPaymentMethod> getChosenPaymentMethodList() {
        return chosenPaymentMethodList;
    }

    public void setChosenPaymentMethodList(List<ChosenPaymentMethod> chosenPaymentMethodList) {
        this.chosenPaymentMethodList = chosenPaymentMethodList;
    }
}
