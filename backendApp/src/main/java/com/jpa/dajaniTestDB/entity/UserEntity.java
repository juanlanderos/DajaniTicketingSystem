package com.jpa.dajaniTestDB.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "username")
    @Size(max = 120)
    private String username;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
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

    public void addTicket(TicketEntity ticketEntity){
        this.ticketEntities.add(ticketEntity);
        ticketEntity.getTicketUsers().add(this);
    }

    public void removeTicket(TicketEntity ticketEntity){
        ticketEntities.remove(ticketEntity);
    }

    public List<TicketEntity> getTicketEntities() {
        return ticketEntities;
    }

    public void setTicketEntities(List<TicketEntity> ticketEntities) {this.ticketEntities = ticketEntities;}
}