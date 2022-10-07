package com.jpa.dajaniTestDB.model;

import com.jpa.dajaniTestDB.entity.TicketEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {
    private Integer commentId;
    private TicketEntity ticketEntity;
    private UserEntity userEntity;
    private String content;
    private String createdAt;
    private String updatedAt;
}
