package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column
    private String telephone;

    @Column
    private String email;

    @Column(name = "current_number_of_orders")
    private int currentNumberOfOrders;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @ManyToMany
    @JoinTable(
            name = "customer_address",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id")}
    )
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private List<Order> orderList;

    public Customer() {
    }

    public long getId() {
        return id;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
