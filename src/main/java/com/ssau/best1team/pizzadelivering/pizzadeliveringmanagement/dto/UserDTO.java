package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto;

import java.util.List;

public class UserDTO {

    private String login;
    private String fullName;
    private List<String> roleList;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
