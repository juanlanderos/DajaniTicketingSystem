package com.jpa.dajaniTestDB.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
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

    @Column(name = "reset_password_token")
    @Size(max = 120)
    private String resetPasswordToken;

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

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "userEntity")
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public void addTicket(TicketEntity ticketEntity){
        this.ticketEntities.add(ticketEntity);
        ticketEntity.getTicketUsers().add(this);
    }

    public void removeTicket(TicketEntity ticketEntity){
        ticketEntities.remove(ticketEntity);
    }
}