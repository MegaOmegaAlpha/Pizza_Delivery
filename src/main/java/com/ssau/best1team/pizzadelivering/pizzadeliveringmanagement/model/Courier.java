package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Courier extends User {

    @Column(name = "identity_number", length = 50)
    private String identityNumber;

    @Column(name = "driver_license", length = 3)
    private String driverLicense;

    @Column(length = 13)
    private String telephone;

    @OneToMany(mappedBy = "courier")
    private List<OrderDelivery> orderDeliveryList;

    public Courier() {
    }

    public Courier(String fullName, String identityNumber, String driverLicense, String telephone) {
        this.fullName = fullName;
        this.identityNumber = identityNumber;
        this.driverLicense = driverLicense;
        this.telephone = telephone;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<OrderDelivery> getOrderDeliveryList() {
        return orderDeliveryList;
    }

    public void setOrderDeliveryList(List<OrderDelivery> orderDeliveryList) {
        this.orderDeliveryList = orderDeliveryList;
    }
}
