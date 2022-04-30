package com.jpa.dajaniTestDB.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "comment")
public class Comment {

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

    @ManyToOne
    @JoinColumn(
            name = "ticket_id",
            referencedColumnName = "ticketId",
            nullable = false
    )
    private Ticket ticket;


    @Lob
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = true
    )
    private User user;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    //------------------------Modifiers------------------------
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

}