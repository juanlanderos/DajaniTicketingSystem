package com.jpa.dajaniTestDB.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Integer userId;
    private Integer admin;
    private Integer agent;
    private Integer requester;
}
