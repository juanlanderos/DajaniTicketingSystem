package com.jpa.dajaniTestDB.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comment")
public class CommentEntity {

    @Id
    //defines a pk sequence for new entries
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    //generates the values needed for pk sequence
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    @Column(name = "comment_id", nullable = false)
    private Integer commentId;

    //owner in relationship to ticketEntity
    @ManyToOne
    @JoinColumn(
            name = "ticket_id",
            referencedColumnName = "ticketId",
            nullable = true
    )
    private TicketEntity ticketEntity;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = true
    )
    private UserEntity userEntity;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    //------------------------Modifiers------------------------
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public TicketEntity getTicketEntity() {
        return ticketEntity;
    }

    public void setTicketEntity(TicketEntity ticketEntity) {
        this.ticketEntity = ticketEntity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserEntity getUserEntity() {return userEntity;}

    public void setUserEntity(UserEntity userEntity) {this.userEntity = userEntity;}
}