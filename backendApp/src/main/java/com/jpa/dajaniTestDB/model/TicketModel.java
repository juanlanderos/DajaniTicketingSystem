package com.jpa.dajaniTestDB.model;

import com.jpa.dajaniTestDB.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketModel {
    private Integer ticketId;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant completedAt;
    private String statusId;
    private Integer assigneeId;
    private Integer requesterId;
    private List<CommentEntity> commentEntityList;
}
