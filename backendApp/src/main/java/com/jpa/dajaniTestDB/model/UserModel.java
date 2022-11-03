package com.jpa.dajaniTestDB.model;

import com.jpa.dajaniTestDB.entity.CommentEntity;
import com.jpa.dajaniTestDB.entity.RoleEntity;
import com.jpa.dajaniTestDB.entity.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Integer userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set <RoleEntity> roles;
    private List<CommentEntity> commentEntityList;
    private List<TicketEntity> ticketEntities;
}
