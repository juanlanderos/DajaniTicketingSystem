package com.jpa.dajaniTestDB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.h2.engine.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "ticketId")
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

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "completed_at")
    private String completedAt;

    @Column(name = "status_ID")
    private String statusId;

    //child in relationship with commentEntity
    @OneToMany(
            mappedBy = "ticketEntity",
            orphanRemoval = true)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "ticketEntities")
            //cascade = { CascadeType.DETACH })
    private List<UserEntity> ticketUsers = new ArrayList<>();

//------------------Modifiers Past This Point ----------------------------

/*    public List<UserEntity> getUserEntityList(){
        return ticketUsers;
    }*/

    public Integer getTicketId() {return ticketId;}

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

    public List<CommentEntity> getCommentEntityList() {return commentEntityList;}

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {this.commentEntityList = commentEntityList;}
}