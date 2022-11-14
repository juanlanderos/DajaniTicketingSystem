package com.jpa.dajaniTestDB.model;

import com.jpa.dajaniTestDB.entity.CommentEntity;
import com.jpa.dajaniTestDB.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketModel {
    private Integer ticketId;
    private String title;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String completedAt;
    private String status;
    private List<CommentEntity> commentEntityList;
    private List<UserEntity> userEntityList;
    private Integer requesterId;
    private Integer agentId;
}
