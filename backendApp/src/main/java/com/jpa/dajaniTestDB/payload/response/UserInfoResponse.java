package com.jpa.dajaniTestDB.payload.response;
import com.jpa.dajaniTestDB.entity.RoleEntity;

import java.util.List;
import java.util.Set;

public class UserInfoResponse {

    private Integer userId;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;

    public UserInfoResponse(Integer userId, String email, String firstName, String lastName,List<String> roles) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
