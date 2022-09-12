package com.jpa.dajaniTestDB.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

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

    @ManyToOne
    @JoinColumn(
            name = "ticket_id",
            referencedColumnName = "ticketId",
            nullable = true
    )
    private TicketEntity ticketEntity;


    @Lob
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = true
    )
    private UserEntity userEntity;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    /* @Column
    @Temporal(TemporalType.DATE)
    Date publicationDate;

    @Column
    @Temporal(TemporalType.TIME)
    Date publicationTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;
    */

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

    /*public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate() {
        this.publicationDate = publicationDate;
    }

    public Date getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime() {
        this.publicationTime = publicationTime;
    }

    public Date getCreationDateTime() {
        return getCreationDateTime();
    }

    public void setCreationDateTime() {
        this.creationDateTime = creationDateTime;
    }
     */
}