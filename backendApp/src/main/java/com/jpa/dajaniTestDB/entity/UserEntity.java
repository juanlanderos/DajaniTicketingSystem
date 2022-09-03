package com.jpa.dajaniTestDB.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_ticket",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "ticket_id") }
    )
    private List<TicketEntity> ticketEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    @Column(name = "admin")
    private Integer admin;

    @Column(name = "agent")
    private Integer agent;

    @Column(name = "requester")
    private Integer requester;

    //----------------Accessors and Modifiers Past this-----------
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Integer getAgent() {
        return agent;
    }

    public void setAgent(Integer agent) {
        this.agent = agent;
    }

    public Integer getRequester() {
        return requester;
    }

    public void setRequester(Integer requester) {
        this.requester = requester;
    }

    public List<CommentEntity> getCommentEntityList() {return commentEntityList;}

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {this.commentEntityList = commentEntityList;}

    public List<TicketEntity> getTicketEntities(){ return ticketEntities;}

    public void setTicketEntities(List<TicketEntity> ticketEntities) {this.ticketEntities = ticketEntities;}


}