package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.*;
import java.util.List;

@Inheritance
@Entity
@Table(name = "user_t")
public abstract class User {

    @Id
    @Column(length = 50)
    protected String login;

    @Column
    protected String password;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roleList;

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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
