package com.jpa.dajaniTestDB.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    //auto-increments primary key per new entry (upon insert)
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    //generates the values needed for pk sequence
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @ManyToMany(fetch = FetchType.LAZY)
            //cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_ticket",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "ticket_id") }
    )
    private List<TicketEntity> ticketEntities = new ArrayList<>();

    @Column(name = "email")
    private String email;

    //----------------Accessors and Modifiers Past this-----------

    public void addTicket(TicketEntity ticketEntity){
        this.ticketEntities.add(ticketEntity);
        ticketEntity.getTicketUsers().add(this);
    }

/*
    public void removeTicket(int ticketId){
        TicketEntity ticketEntity = this.ticketEntities.stream()
                .filter(t -> t.getTicketId() == ticketId).findFirst().orElse(null);

        if(ticketEntity != null){
            this.ticketEntities.remove(ticketEntity);
            ticketEntity.getTicketUsers().remove(this);
        }
    }
*/

    public void removeTicket(TicketEntity ticketEntity){
        ticketEntities.remove(ticketEntity);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail(){
        return email;
    }

    public void setTicketEntities(List<TicketEntity> ticketEntities) {this.ticketEntities = ticketEntities;}
}