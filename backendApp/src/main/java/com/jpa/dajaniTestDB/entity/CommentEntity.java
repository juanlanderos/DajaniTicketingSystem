package com.jpa.dajaniTestDB.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "commentId")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "ticket_id",
            referencedColumnName = "ticketId",
            nullable = true
    )
    private TicketEntity ticketEntity;

    @ManyToOne(fetch = FetchType.EAGER)
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
}