package com.jpa.dajaniTestDB.service.serviceImplementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.dajaniTestDB.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class UserDetailsImpl implements UserDetails {

    private Integer userId;
    private String email;
    private String firstName;
    private String lastName;

    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetailsImpl(Integer userId, String email, String firstName, String lastName, String password,
                                  Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserEntity userModel){
        List<GrantedAuthority> authorities = userModel.getRoles().stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRoleName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                userModel.getUserId(),
                userModel.getEmail(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.userId);
    }
}
