package com.jpa.dajaniTestDB.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    private Integer ticketId;


    @ManyToMany(mappedBy = "ticketEntities")
    private List<UserEntity> userEntityList = new ArrayList<>();

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "completed_at")
    private String completedAt;

    @Column(name = "status_ID")
    private String statusId;

    @Column(name = "assignee_ID")
    private Integer assigneeId;

    @Column(name = "requester_ID")
    private Integer requesterId;

    @OneToMany(mappedBy = "ticketEntity")
    private List<CommentEntity> commentEntityList = new ArrayList<>();

//------------------Modifiers Past This Point ----------------------------

    public Integer getTicketId() {return ticketId;}

    public void setTicketId(Integer ticketId) {this.ticketId = ticketId;}

    public String getCreatedAt() {return createdAt;}

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Integer getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Integer assigneeId) {this.assigneeId = assigneeId;}

    public Integer getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    public List<CommentEntity> getCommentEntityList() {return commentEntityList;}

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {this.commentEntityList = commentEntityList;}

    public List<UserEntity>getUsersEntityList(){
        return userEntityList;
    }
}