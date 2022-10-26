package com.jpa.dajaniTestDB.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    //auto-increments primary key per new entry (upon insert)
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "user_sequence"
    )
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotBlank
    @Column(name = "email")
    @Size(max = 120)
    private String email;

    @NotBlank
    @Column(name = "firstName")
    @Size(max = 120)
    private String firstName;

    @NotBlank
    @Column(name = "lastName")
    @Size(max = 120)
    private String lastName;

    @NotBlank
    @Column(name = "password")
    @Size(max = 120)
    private String password;

    //join table with roleEntity
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
            //cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_ticket",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "ticket_id") }
    )
    private List<TicketEntity> ticketEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public UserEntity(String email, String firstName, String lastName, String password) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UserEntity() {
    }

    //----------------Accessors and Modifiers Past this-----------\
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public void addTicket(TicketEntity ticketEntity){
        this.ticketEntities.add(ticketEntity);
        ticketEntity.getTicketUsers().add(this);
    }

    public void removeTicket(TicketEntity ticketEntity){
        ticketEntities.remove(ticketEntity);
    }



    public String getEmail(){
        return email;
    }

    public List<TicketEntity> getTicketEntities() {
        return ticketEntities;
    }

    public void setTicketEntities(List<TicketEntity> ticketEntities) {this.ticketEntities = ticketEntities;}
}