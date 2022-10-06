package com.jpa.dajaniTestDB.model;

import com.jpa.dajaniTestDB.entity.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Integer userId;
    private String email;
    private List<TicketEntity> ticketEntities;
}
