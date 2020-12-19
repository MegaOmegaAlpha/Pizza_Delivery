package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Inheritance
@Entity
@Table(name = "user_t")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected long id;

    @Column(length = 50)
    protected String login;

    @Column
    protected String password;

    @Column(name = "full_name", length = 50)
    protected String fullName;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roleList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_address",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id")}
    )
    private List<Address> addresses;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
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
